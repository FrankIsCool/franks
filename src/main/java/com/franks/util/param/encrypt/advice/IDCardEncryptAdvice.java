package com.franks.util.param.encrypt.advice;

import com.franks.util.valid.ValidUtils;
import com.franks.util.param.ParamAdvice;
import com.franks.util.param.encrypt.annotation.IDCardEncrypt;
import org.springframework.stereotype.Component;

/**
 * 手机号加密
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
@Component
public class IDCardEncryptAdvice {
    /**
     * 对含注解字段加密
     *
     * @param t
     */
    public static <T> void encryptField(T t) {
        ParamAdvice.encryptField(t, IDCardEncrypt.class, IDCardEncryptAdvice::idCardEncry);
    }

    /**
     * 身份证加密
     *
     * @param idCard 身份证
     * @return
     */
    public static String idCardEncry(String idCard) {
        if (ValidUtils.isIDCard(idCard)) {
            return idCard;
        }
        if(idCard.length()==15){
            return idCard.replaceAll("(\\d{4})\\d{7}(\\d{4})", "$1**********$2");
        }
        return idCard.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1**********$2");
    }
}