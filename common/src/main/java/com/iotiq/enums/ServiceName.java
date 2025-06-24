package com.iotiq.enums;

public enum ServiceName {
    AUTH("Auth Service"),
    USER("User Service");

    private final String displayName;

    ServiceName(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
