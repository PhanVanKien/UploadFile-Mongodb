package com.demo.fileUpload.exception;

import com.demo.fileUpload.model.TestValidateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.ManagedBean;

@ManagedBean
@RequestScope
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContext {
    public TestValidateDTO testValidateDTO;
}
