package com.rest.model;

import java.io.IOException;

public class WebApiException extends IOException {
    private int code;
    private String errorMessage;

    public WebApiException(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getMessage() {
        return String.format("%s: %s", code, errorMessage);
    }
}
