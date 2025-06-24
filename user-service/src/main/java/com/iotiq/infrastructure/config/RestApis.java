package com.iotiq.infrastructure.config;

public class RestApis {
    public static final String DEVELOPER = "/dev";
    public static final String TEST = "/test";
    public static final String RELEASE = "/prod";
    public static final String VERSION = "/v1";

    public static final String USER_PROFILE = DEVELOPER + VERSION + "/users";

    public static final String CREATE_USER = "/create-user";
    public static final String GET_USER = "/get-user";
}
