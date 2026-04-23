package com.crossly.backend.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crossly.backend.repository.UserProfileRepository;
import com.crossly.backend.service.UserProfileService;
import com.crossly.backend.service.impl.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserProfileRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = UserProfileService.extractToken(request);
        if (token != null && jwtService.isTokenValid(token)) {
            var username = jwtService.extractSubject(token);
            UserDetails user = userRepository.findByUsername(username).orElse(null);
            if (user != null) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
