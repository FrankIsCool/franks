package com.franks.util.param.encrypt;


import com.franks.util.empty.EmptyUtil;

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
        if (EmptyUtil.isEmpty(phone)) {
            return phone;
        }
        if ((phone = phone.trim()).length() > 7) {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else if (phone.length() <= 3) {
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
        if (EmptyUtil.isEmpty(idCard) || idCard.length() < 8) {
            return idCard;
        }
        StringBuilder s = new StringBuilder();
        s.append(idCard.substring(0, 4));
        s.append("**********");
        s.append(idCard.substring(idCard.length() - 4));
        return s.toString();
    }
}
