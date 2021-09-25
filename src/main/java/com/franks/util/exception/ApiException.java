package com.franks.util.exception;

/**
 * 异常类
 *
 * @author frank
 * @module
 * @date 2021/9/25 11:49
 */
public class ApiException extends RuntimeException {
    //异常码
    private String errorCode;

    public ApiException(String errorMsg) {
        super(errorMsg);
    }

    public ApiException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static ApiException of(String code, String text) {
        return new ApiException(code, text);
    }

    public static ApiException of(String text) {
        return new ApiException(text);
    }

    public static ApiException of(Throwable cause) {
        return new ApiException(cause);
    }
}
