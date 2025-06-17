package com.iotiq.api.dto;

public record ApiResponse<T>(String message, T data) { }

