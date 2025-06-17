package com.iotiq.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApplicationException {
    public UserAlreadyExistsException(String email, String userName) {
        super(HttpStatus.CONFLICT, ErrorCode.USER_ALREADY_EXISTS, "User already exists with email: " + email + " or username: " + userName);
    }
}
