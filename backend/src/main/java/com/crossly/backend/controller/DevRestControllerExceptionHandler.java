package com.crossly.backend.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crossly.backend.dto.ErrorResponse;
import com.crossly.backend.exception.AuthException;

@Profile("dev")
@RestControllerAdvice
public class DevRestControllerExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(AuthException e) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("The request was not valid!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
