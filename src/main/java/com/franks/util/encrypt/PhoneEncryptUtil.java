package com.franks.util.encrypt;

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
public class PhoneEncryptUtil {
    /**
     * 对含注解字段加密
     *
     * @param t
     */
    public static <T> void encryptField(T t) {
        EncryptParamUtil.encryptField(t, PhoneEncrypt.class, content -> ValidUtils.isMobile(content) ? EncryptUtil.phoneEncry(content) : content);
    }
}