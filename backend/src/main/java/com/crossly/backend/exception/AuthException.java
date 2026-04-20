package com.crossly.backend.exception;

import org.jspecify.annotations.Nullable;

public class AuthException extends RuntimeException {

    public AuthException(@Nullable String msg) {
        super(msg);
    }

    public static AuthException invalidCredentials() {
        return new AuthException("Invalid login credentials!");
    }

    public static AuthException usernameExists() {
        return new AuthException("Username already exists!");
    }

}
