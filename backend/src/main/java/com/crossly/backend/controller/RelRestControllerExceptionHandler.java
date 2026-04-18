package com.crossly.backend.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crossly.backend.dto.ErrorResponse;

@Profile("rel")
@RestControllerAdvice
public class RelRestControllerExceptionHandler extends DevRestControllerExceptionHandler {

    /**
     * Handles any generic runtime exception not handled by any previously
     * decleared handler
     *
     * @param e The generic runtime exception
     * @return A not found error response entity
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGenericRuntimeException(RuntimeException e) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Resource could not be found!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any generic non runtime exception not handled by any previously
     * decleared handler
     *
     * @param e The generic exception
     * @return An internal server error response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An internal server error has occured!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
