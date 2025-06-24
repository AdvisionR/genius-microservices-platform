package com.iotiq.infrastructure.config;

public class RestApis {
    public static final String DEVELOPER = "/dev";
    public static final String TEST = "/test";
    public static final String RELEASE = "/prod";
    public static final String VERSION = "/v1";

    public static final String AUTH = DEVELOPER + VERSION + "/auth";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String ME = "/me";

    public static final String CREATE_USER = "/create-user";
    public static final String GET_USER = "/get-user";
}
