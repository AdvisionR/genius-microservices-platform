package com.iotiq.infrastructure.config;

public class RestApis {
    public static final String API = "/api";
    public static final String VERSION = "/v1";

    public static final String CHAT = API + VERSION + "/chats";
    public static final String MESSAGE = API + VERSION + "/messages";

    public static final String CREATE = "/create";
    public static final String GET_ALL = "/get-all";
    public static final String GET_CHAT = "/get/{chatUuid}";
    public static final String GET_USER = "/get-user";
}
