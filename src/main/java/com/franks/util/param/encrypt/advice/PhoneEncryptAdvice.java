package com.franks.util.param.encrypt.advice;

import com.franks.util.param.ParamUtil;
import com.franks.util.param.encrypt.EncryptUtil;
import com.franks.util.param.encrypt.annotation.PhoneEncrypt;
import com.franks.util.empty.ValidUtils;
import org.springframework.stereotype.Component;

/**
 * 手机号加密
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
@Component
public class PhoneEncryptAdvice {
    /**
     * 对含注解字段加密
     *
     * @param t
     */
    public static <T> void encryptField(T t) {
        ParamUtil.encryptField(t, PhoneEncrypt.class, content -> ValidUtils.isMobile(content) ? EncryptUtil.phoneEncry(content) : content);
    }
}