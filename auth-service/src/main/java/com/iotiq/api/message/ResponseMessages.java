package com.iotiq.api.message;

import lombok.Getter;

@Getter
public enum ResponseMessages {
    LOGIN_SUCCESSFUL("Login successfully");

    private final String message;

    ResponseMessages(String message) {
        this.message = message;
    }
}
