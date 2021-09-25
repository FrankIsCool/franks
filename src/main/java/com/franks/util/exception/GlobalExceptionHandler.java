package com.franks.util.exception;

import com.franks.util.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

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
     * 参数错误异常处理-post
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
        logger.error("请求的参数有误",mapErrFields);
        return ApiResponse.of("600", "请求的参数有误: " + mapErrFields.values(), mapErrFields);
    }

    /**
     * 参数错误异常处理-get
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ApiResponse BindExceptionHandler(HttpServletRequest request, BindException exception) {
        Map<String, String> mapErrFields = new HashMap<String, String>();
        for (FieldError err : exception.getBindingResult().getFieldErrors()) {
            mapErrFields.put(err.getField(), err.getDefaultMessage());
        }
        logger.error("请求的参数有误",mapErrFields);
        return ApiResponse.of("600", "请求的参数有误: " + mapErrFields.values(), mapErrFields);
    }

    /**
     * 自定义异常信息
     *
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ApiResponse apiExceptionHandler(HttpServletRequest request, ApiException exception) {
        logger.error("自定义异常信息",exception);
        return ApiResponse.error(exception.getErrorCode(), exception.getMessage());
    }

    /**
     * 未找到异常处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResponse noHandlerFoundExceptionHandler(HttpServletRequest request, NoHandlerFoundException exception) {
        logger.error("该功能未找到",exception);
        return ApiResponse.error("404", "该功能未找到");
    }

    /**
     * 数据库运行异常处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public ApiResponse sqlExceptionHandler(HttpServletRequest request, SQLException exception) {
        logger.error("数据库处理异常",exception);
        return ApiResponse.error("1046", "数据库处理异常");
    }

    /**
     * 数据库异常处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(DataAccessException.class)
    public ApiResponse dataAccessExceptionHandler(HttpServletRequest request, DataAccessException exception) {
        logger.error("数据库异常",exception);
        return ApiResponse.error("1036", "数据库异常");
    }

    /**
     * 数据库异常处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BadSqlGrammarException.class)
    public ApiResponse badSqlGrammarExceptionHandler(HttpServletRequest request, BadSqlGrammarException exception) {
        logger.error("数据库异常",exception);
        return ApiResponse.error("1036", "数据库异常");
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
        logger.error("默认异常处理",exception);
        return ApiResponse.error("500", exception.getMessage());
    }
}