package com.demo.fileUpload.controller;

import com.demo.fileUpload.exception.RequestContext;
import com.demo.fileUpload.model.LoadFile;
import com.demo.fileUpload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;
import com.demo.fileUpload.model.TestValidateDTO;

@RestController
@CrossOrigin("*")
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    public RequestContext requestContext;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileService.addFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        LoadFile loadFile = fileService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }

    @PostMapping("/test-validate")
    public ResponseEntity<?> testValidate(@RequestBody @Valid TestValidateDTO dto, WebRequest webRequest) throws IOException {
        requestContext.setTestValidateDTO(dto);
        webRequest.setAttribute("person", dto, RequestAttributes.SCOPE_REQUEST);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/downloadZipFile")
    public void downloadAsZip(HttpServletResponse response) throws IOException {

        //Getting the time in milliseconds to create the zip file name
        Calendar calendar = Calendar.getInstance();
        String zipFileName = calendar.getTimeInMillis() + ".zip";

        //set headers to the response
        response.setContentType("application/zip");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipFileName + "\"");

        //retrieve zip file to the response
        fileService.downloadFilesAsZip(response);

        //set status to OK
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @PostMapping("/getSize")
    public int getSize(@RequestBody String text) throws IOException {
        return calSizeFromBase64(text) / 1024;
    }

    public int calSizeFromBase64(String text) {
        int count = 0;
        int pad = 0;
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if (c == '=') pad++;
            if (!Character.isWhitespace(c)) count++;
        }

        return (count * 3 / 4) - pad;
    }
}
