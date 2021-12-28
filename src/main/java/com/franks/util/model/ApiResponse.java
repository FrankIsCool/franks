package com.franks.util.model;

import com.franks.util.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 统一返回实体类
 *
 * @author frank
 * @date 2021/9/18 10:04
 */
@Data
@ApiModel("统一返回实体类")
public class ApiResponse<T> {
    /**
     * 返回码
     */
    @ApiModelProperty("返回码")
    private Integer code;
    /**
     * 返回错误信息
     */
    @ApiModelProperty("返回错误信息")
    private String text;
    /**
     * 返回对象
     */
    @ApiModelProperty("返回对象")
    private T data;

    public ApiResponse(Integer code, String text, T data) {
        this.code = code;
        this.text = text;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(Integer code, String text, T data) {
        return new ApiResponse(code, text, data);
    }

    public static ApiResponse<String> error(Integer code, String text) {
        return of(code, text, Constant.FAIL_MSG);
    }

    public static ApiResponse<String> error(String code, String text) {
        return of(Integer.valueOf(code), text, Constant.FAIL_MSG);
    }

    public static ApiResponse<String> error(String text) {
        return of(Constant.FAIL_CODE, text, Constant.FAIL_MSG);
    }

    public static ApiResponse<String> error() {
        return of(Constant.FAIL_CODE, Constant.FAIL_EN_MSG, Constant.FAIL_MSG);
    }

    public static <T> ApiResponse<T> success(T data) {
        return of(Constant.SUCCESS_CODE, Constant.SUCCESS_EN_MSG, data);
    }

    public static ApiResponse<String> success() {
        return of(Constant.SUCCESS_CODE, Constant.SUCCESS_EN_MSG, Constant.SUCCESS_MSG);
    }
}
