package com.crossly.backend.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crossly.backend.dto.AuthRequest;
import com.crossly.backend.dto.AuthResponse;
import com.crossly.backend.entity.UserProfile;
import com.crossly.backend.exception.AuthException;
import com.crossly.backend.repository.UserProfileRepository;
import com.crossly.backend.service.UserProfileService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Profile("rel")
@Service
@RequiredArgsConstructor
public class RelUserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse registerUser(AuthRequest request, HttpServletResponse response) {
        var user = userProfileRepository.findByUsername(request.username().trim());
        if (user.isPresent())
            throw AuthException.usernameExists();
        userProfileRepository.save(UserProfile.builder()
                .username(request.username().trim())
                .password(passwordEncoder.encode(request.password()))
                .build());
        return AuthResponse.successfulRegiseter();
    }

    @Override
    public AuthResponse loginUser(AuthRequest request, HttpServletResponse response) throws UsernameNotFoundException {
        var user = userProfileRepository.findByUsername(request.username().trim())
                .orElseThrow(() -> AuthException.invalidCredentials());
        if (passwordEncoder.matches(request.password(), user.getPassword()))
            return AuthResponse.successfulLogin();

        throw new AuthException("Invalid login credentials!");
    }

}
