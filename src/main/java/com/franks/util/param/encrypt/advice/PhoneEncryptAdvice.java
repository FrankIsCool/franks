package com.franks.util.param.encrypt.advice;

import com.franks.util.valid.ValidUtils;
import com.franks.util.param.ParamUtil;
import com.franks.util.param.encrypt.annotation.PhoneEncrypt;
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
        ParamUtil.encryptField(t, PhoneEncrypt.class, PhoneEncryptAdvice::phoneEncry);
    }

    /**
     * 手机号加密
     *
     * @param phone 手机号
     * @return 134****001
     * @author frank(付帅)
     * @date 2020/7/7
     **/
    public static String phoneEncry(String phone) {
        if (!ValidUtils.isMobile(phone)) {
            return phone;
        }
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}