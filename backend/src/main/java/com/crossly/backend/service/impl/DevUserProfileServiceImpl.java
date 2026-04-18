package com.crossly.backend.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.crossly.backend.dto.AuthRequest;
import com.crossly.backend.dto.AuthResponse;
import com.crossly.backend.entity.UserProfile;
import com.crossly.backend.exception.AuthException;
import com.crossly.backend.service.UserProfileService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Profile("dev")
@Service
@RequiredArgsConstructor
public class DevUserProfileServiceImpl implements UserProfileService {

    private final InMemoryUserDetailsManager userProfileRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final JwtService jwtService;

    public AuthResponse registerUser(AuthRequest request, HttpServletResponse response) throws AuthException {
        try {
            var user = userProfileRepository.loadUserByUsername(request.username().trim());
            if (user != null)
                throw AuthException.usernameExists();
        } catch (UsernameNotFoundException e) {
        }
        userProfileRepository.createUser(UserProfile.builder()
                .username(request.username().trim())
                .password(passwordEncoder.encode(request.password()))
                .build());
        var token = jwtService.generateToken(
                userProfileRepository.loadUserByUsername(request.username().trim()));
        attachTokenCookie(response, token);
        return AuthResponse.successfulRegiseter();
    }

    @Override
    public AuthResponse loginUser(AuthRequest request, HttpServletResponse response) throws UsernameNotFoundException {
        try {
            var user = userProfileRepository.loadUserByUsername(request.username().trim());
            if (passwordEncoder.matches(request.password(), user.getPassword())) {
                var token = jwtService.generateToken(user);
                attachTokenCookie(response, token);
                return AuthResponse.successfulLogin();
            }
            throw AuthException.invalidCredentials();
        } catch (UsernameNotFoundException e) {
            throw AuthException.invalidCredentials();
        }
    }

}
