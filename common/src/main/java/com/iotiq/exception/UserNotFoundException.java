package com.iotiq.exception;

import com.iotiq.message.ResponseMessages;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApplicationException{
    public UserNotFoundException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.USER_NOT_FOUND, ResponseMessages.USER_NOT_FOUND);
    }
}
