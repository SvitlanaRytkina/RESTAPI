package com.rest.model;

public class WebApiResponse {
    private int code;
    private String message;

    public WebApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getResponse() {
        return String.format("[ %s: %s ]", code, message);
    }
}
