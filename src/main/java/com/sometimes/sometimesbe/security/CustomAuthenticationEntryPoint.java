package com.sometimes.sometimesbe.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sometimes.sometimesbe.utils.ErrorCode;
import com.sometimes.sometimesbe.utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode exception = (ErrorCode) request.getAttribute("exception");

        if (exception.equals(ErrorCode.NOT_FOUND_TOKEN)) {
            exceptionHandler(response, ErrorCode.NOT_FOUND_TOKEN);
            return;
        }

        if (exception.equals(ErrorCode.INVALID_TOKEN)) {
            exceptionHandler(response, ErrorCode.INVALID_TOKEN);
            return;
        }

        if (exception.equals(ErrorCode.NOT_FOUND_USER)) {
            exceptionHandler(response, ErrorCode.NOT_FOUND_USER);
        }
    }

    public void exceptionHandler(HttpServletResponse response, ErrorCode error) {
        response.setStatus(error.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ErrorResponse.toResponseEntity(error));
            response.getWriter().write(json);
            log.error(error.getMsg());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
