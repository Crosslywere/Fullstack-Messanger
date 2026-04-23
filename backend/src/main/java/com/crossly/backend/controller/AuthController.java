package com.crossly.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossly.backend.dto.AuthRequest;
import com.crossly.backend.dto.AuthResponse;
import com.crossly.backend.service.UserProfileService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserProfileService userProfileService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid AuthRequest request,
            HttpServletResponse response) {
        return ResponseEntity.ok(userProfileService.registerUser(request, response));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request,
            HttpServletResponse response) {
        return ResponseEntity.ok(userProfileService.loginUser(request, response));
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logoutUser(HttpServletResponse response) {
        UserProfileService.removeJwtToken(response);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateConnection() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null)
            return ResponseEntity.ok().body(true);
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

}
