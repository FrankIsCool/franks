package com.franks.util.param.valid.advice;

import com.franks.util.param.ParamUtil;
import com.franks.util.exception.ApiException;
import com.franks.util.valid.ValidUtils;
import com.franks.util.param.valid.annotation.IDCardValid;
import org.springframework.stereotype.Component;

/**
 * 手机号加密
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
@Component
public class IDCardValidAdvice {
    /**
     * 对含注解字段加密
     *
     * @param t
     */
    public static <T> void vaildField(T t) {
        ParamUtil.validField(t, IDCardValid.class, content -> {
            if (ValidUtils.isIDCard(content)) {
                throw new ApiException("身份证格式错误");
            }
        });
    }
}