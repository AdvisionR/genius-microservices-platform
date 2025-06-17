package com.iotiq.infrastructure.exception;

import com.iotiq.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now().toEpochMilli())
                .status(ex.getStatus().value())
                .error(ex.getStatus().getReasonPhrase())
                .code(ex.getCode())
                .messagePrefix(ex.getMessagePrefix())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }
}

