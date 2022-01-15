package com.dwf20221api.config.security.utils;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import com.dwf20221api.config.security.AccessToken;


public class JwtAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private final AccessToken accessToken;

    public JwtAuthentication(AccessToken accessToken) {
        super(accessToken.getAuthorities());
        this.accessToken = accessToken;
    }

    @Override
    public Object getCredentials() {
        return accessToken.getValueAsString();
    }

    @Override
    public Object getPrincipal() {
        return accessToken.getUsername();
    }
}
