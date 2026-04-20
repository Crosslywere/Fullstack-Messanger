package com.crossly.backend.dto;

public record AuthResponse(boolean success, String message) {

    public static AuthResponse successfulLogin() {
        return new AuthResponse(true, "Successfully logged in!");
    }

    public static AuthResponse failed() {
        return new AuthResponse(false, "Failed request!");
    }

    public static AuthResponse successfulRegiseter() {
        return new AuthResponse(true, "Registered successfully!");
    }

}
