package com.demo.fileUpload.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestValidateDTO {
    @NotNull
    @NotEmpty(message = "FileName is empty")
    @Length(max = 10, message = "Max length is 10")
    private String fileName;

    @NotNull(message = "Type is invalid")
    @Range(min=0, max = 1, message = "Type is > 1")
    private Integer uploadType;

    @NotNull
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private String createDate;
    private String createTime;
}
