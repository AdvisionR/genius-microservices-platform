package com.iotiq.exception;

import com.iotiq.message.ResponseMessages;
import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends ApplicationException {
    public TokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.TOKEN_NOT_FOUND, ResponseMessages.INVALID_CREDENTIALS);
    }
}
