package com.crossly.backend.service;

import com.crossly.backend.dto.AuthRequest;
import com.crossly.backend.dto.AuthResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public interface UserProfileService {

    static final String JWT_COOKIE_NAME = "json_web_token";

    AuthResponse registerUser(AuthRequest request, HttpServletResponse response);

    AuthResponse loginUser(AuthRequest request, HttpServletResponse response);

    default void removeJwtToken(HttpServletResponse response) {
        var cookie = new Cookie(JWT_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    default void attachTokenCookie(HttpServletResponse response, String token) {
        var cookie = new Cookie(JWT_COOKIE_NAME, token);
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

}
