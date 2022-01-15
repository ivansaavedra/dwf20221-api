package com.dwf20221api.config.security.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public class AuthorizationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(createErrorBody(accessDeniedException));
    }

    private String createErrorBody(AccessDeniedException exception) {
        JsonObject exceptionMessage = new JsonObject();
        exceptionMessage.addProperty("Código", HttpStatus.FORBIDDEN.value());
        exceptionMessage.addProperty("Razón", HttpStatus.FORBIDDEN.getReasonPhrase());
        exceptionMessage.addProperty("Timestamp", Instant.now().toString());
        exceptionMessage.addProperty("Mensaje", exception.getMessage());
        return new Gson().toJson(exceptionMessage);
    }
}