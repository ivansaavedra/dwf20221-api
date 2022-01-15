package com.dwf20221api.config.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

	private static final long serialVersionUID = -4742446427770939602L;

	public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
