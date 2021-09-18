package com.franks.util.exception;

import com.franks.util.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常拦截
 *
 * @author frank
 * @module
 * @date 2021/9/18 10:26
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数错误异常处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse argumentErrorHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        Map<String, String> mapErrFields = new HashMap<>();
        for (FieldError err : exception.getBindingResult().getFieldErrors()) {
            mapErrFields.put(err.getField(), err.getDefaultMessage());
        }
        return ApiResponse.of(600, "请求的参数有误: " + mapErrFields.values(), mapErrFields);
    }

    /**
     * 默认异常处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResponse defaultErrorHandler(HttpServletRequest request, Exception exception) {
        if (exception instanceof ApiException) {
            return ApiResponse.error(((ApiException) exception).getErrorCode(), exception.getMessage());
        } else if (exception instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return ApiResponse.error(404, "该功能未找到");
        } else if (exception instanceof SQLException) {
            return ApiResponse.error(1046, "数据库处理异常");
        } else if (exception instanceof DataAccessException || exception instanceof BadSqlGrammarException) {
            return ApiResponse.error(1036, "数据库异常");
        }
        return ApiResponse.error(500, exception.getMessage());
    }
}