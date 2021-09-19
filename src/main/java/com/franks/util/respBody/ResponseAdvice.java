package com.franks.util.respBody;

import com.franks.util.empty.EmptyUtil;
import com.franks.util.model.ApiResponse;
import com.franks.util.mysql.DBRSA;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 返回参数拦截
 *
 * @module
 * @author frank
 * @date 2021/9/19 15:40
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> converter, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (!(object instanceof ApiResponse)) {
            object = ApiResponse.success(object);
        }
        ApiResponse apiResponse = (ApiResponse) object;
        if (apiResponse.getCode() != 200 || EmptyUtil.isEmpty(apiResponse.getData())) {
            return apiResponse;
        }
        Object obj = apiResponse.getData();
        if (obj instanceof List) {
            List<?> datas = (List<?>) obj;
            Field[] fields = datas.get(0).getClass().getDeclaredFields();
            int len;
            if (0 < (len = fields.length)) {
                boolean isD = false;
                for (int i = 0; i < len; i++) {
                    if (fields[i].isAnnotationPresent(EncryptField.class)) {
                        isD = true;
                        break;
                    }
                }
                if (isD) {
                    datas.forEach(DBRSA::encryptField);
                }
            }
            return ApiResponse.success(datas);
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        int len;
        if (0 < (len = fields.length)) {
            boolean isD = false;
            for (int i = 0; i < len; i++) {
                if (fields[i].isAnnotationPresent(EncryptField.class)) {
                    isD = true;
                    break;
                }
            }
            if (isD) {
                DBRSA.encryptField(obj);
            }
        }
        return apiResponse;
    }

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> httpMessageConverter) {
        return true;
    }

}