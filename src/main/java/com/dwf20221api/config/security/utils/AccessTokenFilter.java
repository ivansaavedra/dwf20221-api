package com.dwf20221api.config.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.dwf20221api.config.security.AccessToken;
import com.dwf20221api.config.security.InvalidTokenException;
import com.dwf20221api.config.security.JwtTokenValidator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class AccessTokenFilter extends AbstractAuthenticationProcessingFilter {

	 private static final Logger log  = LoggerFactory.getLogger(AccessTokenFilter.class);
    private final JwtTokenValidator tokenVerifier;

    public AccessTokenFilter(
            JwtTokenValidator jwtTokenValidator,
            AuthenticationManager authenticationManager,
            AuthenticationFailureHandler authenticationFailureHandler) {

        super(AnyRequestMatcher.INSTANCE);
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        this.tokenVerifier = jwtTokenValidator;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {

        log.info("Intentando autenticar para petición {}", request.getRequestURI());

        String authorizationHeader = extractAuthorizationHeaderAsString(request);
        AccessToken accessToken = tokenVerifier.validateAuthorizationHeader(authorizationHeader);
        return this.getAuthenticationManager()
                .authenticate(new JwtAuthentication(accessToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("Se ha autenticado correctamente para la petición {}", request.getRequestURI());

        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    private String extractAuthorizationHeaderAsString(HttpServletRequest request) {
        try {
            return request.getHeader("Authorization");
        } catch (Exception ex){
            throw new InvalidTokenException("No hay encabezado de autorización en los encabezados (headers)", ex);
        }
    }
}
