package com.franks.util.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    public static ApiResponse error(Integer code, String text) {
        return of(code, text, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return of(null, null, data);
    }
}
