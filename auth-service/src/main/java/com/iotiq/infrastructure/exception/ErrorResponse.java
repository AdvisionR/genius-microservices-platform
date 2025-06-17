package com.iotiq.infrastructure.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final Long timestamp;
    private final int status;
    private final String error;
    private final int code;
    private final String messagePrefix;
    private final String message;
    private final String path;
}
