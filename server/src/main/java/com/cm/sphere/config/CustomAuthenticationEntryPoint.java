package com.cm.sphere.config;

import com.cm.sphere.exception.BaseCustomAuthException;
import com.cm.sphere.model.error.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    public CustomAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        int httpStatus = HttpStatus.UNAUTHORIZED.value();

        if (authException instanceof BaseCustomAuthException) {
            httpStatus = ((BaseCustomAuthException) authException).getHttpStatus().value();
        }

        System.out.println("HIIIIIIIIIIIIIII" + authException);

        response.setStatus(httpStatus);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        logger.error("An AuthenticationException occurred: {}", authException.getMessage());

        final ApiError data = new ApiError(HttpStatus.valueOf(httpStatus), authException.getMessage());

        final OutputStream out = response.getOutputStream();
        mapper.writeValue(out, data);
        out.flush();
    }
}
