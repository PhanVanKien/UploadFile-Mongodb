package com.demo.fileUpload.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ECMUtil {
    private final static Logger logger = LoggerFactory.getLogger(ECMUtil.class);
    public static String BaseFolderTemporary = "/Users/mac/Documents";
    public static String BaseFolderPermanent = "/Users/mac/Documents";

    public static void main(String[] args) throws IOException {
        getFolder(false);
    }

    private static final String[] VietnameseSigns = new String[]
            {

                    "aAeEoOuUiIdDyY",

                    "áàạảãâấầậẩẫăắằặẳẵ",

                    "ÁÀẠẢÃÂẤẦẬẨẪĂẮẰẶẲẴ",

                    "éèẹẻẽêếềệểễ",

                    "ÉÈẸẺẼÊẾỀỆỂỄ",

                    "óòọỏõôốồộổỗơớờợởỡ",

                    "ÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠ",

                    "úùụủũưứừựửữ",

                    "ÚÙỤỦŨƯỨỪỰỬỮ",

                    "íìịỉĩ",

                    "ÍÌỊỈĨ",

                    "đ",

                    "Đ",

                    "ýỳỵỷỹ",

                    "ÝỲỴỶỸ"
            };

    public static String getFolder(boolean isTemp) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        String date = ZonedDateTime.now().format(formatter);
        String y = ZonedDateTime.now().format(year);
        String m = ZonedDateTime.now().format(month);

        String dir = isTemp == true ? BaseFolderTemporary + "/" + date : BaseFolderPermanent + "/" + y
                + "/" + m + "/" + date;
        Path path = Paths.get(dir);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        return path.toString();
    }

//    public static void saveZipFile(String filePath, String fileName, String fileData) {
//        using (MemoryStream ms = new MemoryStream())
//        {
//            using (ZipArchive arch = new ZipArchive(ms, ZipArchiveMode.Create, true))
//            {
//                ZipArchiveEntry zae = arch.CreateEntry(fileName);
//                using (StreamWriter sw = new StreamWriter(zae.Open()))
//                {
//                    sw.Write(fileData);
//                }
//            }
//            using (FileStream fileStream = new FileStream($"{filePath}/{fileName}.zip", FileMode.Create))
//            {
//                ms.Seek(0, SeekOrigin.Begin);
//                ms.CopyTo(fileStream);
//            }
//        }
//    }
}
