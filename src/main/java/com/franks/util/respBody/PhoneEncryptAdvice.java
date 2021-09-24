package com.franks.util.respBody;

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
        EncryptFieldAdvice.encryptField(t, PhoneEncrypt.class, new IEncryptField() {
            @Override
            public String encrypt(String content) {
                return ValidUtils.isMobile(content) ? EncryptUtil.phoneEncry(content) : content;
            }

            @Override
            public String decrypt(String content) {
                return null;
            }
        });
    }
}