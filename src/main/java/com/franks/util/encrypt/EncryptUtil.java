package com.franks.util.encrypt;

import org.apache.commons.lang.StringUtils;

/**
 * 手机号身份证加密
 *
 * @author frank
 * @module
 * @date 2021/9/24 14:59
 */
public class EncryptUtil {

    /**
     * 手机号加密
     *
     * @param phone 手机号
     * @return 134****001
     * @author frank(付帅)
     * @date 2020/7/7
     **/
    public static String phoneEncry(String phone) {
        if (phone.isEmpty() || phone.trim().length() < 1) {
            return phone;
        }
        phone = phone.trim();
        if (phone.length() > 7) {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        if (phone.length() <= 3) {
            return "****";
        }
        return phone.substring(0, 3) + "****";
    }

    /**
     * 身份证加密
     *
     * @param idCard 身份证
     * @return
     */
    public static String idCardEncry(String idCard) {
        if (StringUtils.isEmpty(idCard) || idCard.length() < 8) {
            return idCard;
        }
        int count = idCard.length();
        StringBuilder s = new StringBuilder();
        s.append(idCard.substring(0, 4));
        s.append("**********");
        s.append(idCard.substring(count - 4));
        return s.toString();
    }
}
