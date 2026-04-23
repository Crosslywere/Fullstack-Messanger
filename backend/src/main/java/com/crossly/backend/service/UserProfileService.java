package com.crossly.backend.service;

import java.util.Arrays;

import com.crossly.backend.dto.AuthRequest;
import com.crossly.backend.dto.AuthResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserProfileService {

    static final String JWT_COOKIE_NAME = "json_web_token";

    AuthResponse registerUser(AuthRequest request, HttpServletResponse response);

    AuthResponse loginUser(AuthRequest request, HttpServletResponse response);

    public static void removeJwtToken(HttpServletResponse response) {
        var cookie = new Cookie(JWT_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void attachTokenCookie(HttpServletResponse response, String token) {
        var cookie = new Cookie(JWT_COOKIE_NAME, token);
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public static String extractToken(HttpServletRequest request) {
        var cookies = request.getCookies();
        if (cookies == null)
            return null;
        return Arrays.asList(cookies).stream()
                .filter(cookie -> cookie.getName().equals(JWT_COOKIE_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

}
