package com.franks.util.exception;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
    Integer errorCode;

    public ApiException(String errorMsg) {
        super(errorMsg);
        this.errorCode = 0;
    }

    public ApiException(Integer errorCode) {
        super("");
        this.errorCode = errorCode;
    }

    public ApiException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public static ApiException of(Integer code, String text) {
        return new ApiException(code, text);
    }

    public static ApiException of(Integer code) {
        return new ApiException(code);
    }

    public static ApiException of(String text) {
        return new ApiException(text);
    }
}
