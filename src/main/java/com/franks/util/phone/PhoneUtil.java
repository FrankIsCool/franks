package com.franks.util.phone;

import com.franks.util.valid.ValidUtils;

public class PhoneUtil {
    //手机号码正则
    public static final String isPhoneNumber = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
    /**
     * 手机号加密
     *
     * @param phone 手机号
     * @return 134****001
     * @author frank(付帅)
     * @date 2020/7/7
     **/
    public static String phoneEncry(String phone) {
        if (!isPhone(phone)) {
            return phone;
        }
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 判断手机
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isPhone(String mobile) {
        return ValidUtils.valid(isPhoneNumber,mobile);
    }
}
