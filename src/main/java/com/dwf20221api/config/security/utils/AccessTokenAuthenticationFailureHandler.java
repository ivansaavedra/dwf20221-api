package com.dwf20221api.config.security.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

class AccessTokenAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException {


        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(createErrorBody(e));
    }

    private String createErrorBody(AuthenticationException exception) {
        JsonObject exceptionMessage = new JsonObject();
        exceptionMessage.addProperty("Código", HttpStatus.UNAUTHORIZED.value());
        exceptionMessage.addProperty("Motivo", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        exceptionMessage.addProperty("Timestamp", Instant.now().toString());
        exceptionMessage.addProperty("Mensaje", exception.getMessage());
        return new Gson().toJson(exceptionMessage);
    }
}
