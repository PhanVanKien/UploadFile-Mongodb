package com.demo.fileUpload.filter;


import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(
                (HttpServletRequest) servletRequest
        );

        filterChain.doFilter(wrapper, servletResponse);
    }
}
