package com.franks.util.empty;

import com.franks.util.param.valid.IDCardVaildUtil;

import java.util.regex.Pattern;

/**
 * 数据格式校验
 *
 * @author frank
 * @date 2021/9/18 11:16
 */
public class ValidUtils {
    // 手机号码正则
    public static final String isPhoneNumber = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
    // 验证网址Url
    public static final String isHttpUrl = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    //验证邮箱
    public static final String isEamil = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    // 验证设备号
    public static final String isImei = "^[0-9A-Za-z]{14,70}$";
    // 验证用户的生日(yyyy-mm-dd)格式
    public static final String isBirthday = "\\d{4}-\\d{2}-\\d{2}";
    // 只能输入非零的正整数
    public static final String isPositiveInteger = "^\\+?[1-9][0-9]*$";
    //判断是否为汉字
    public static final String isChinese = "^[\u4e00-\u9fa5]+$";
    //判断是否ip地址
    public static final String isIP = "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)";

    public static boolean valid(String regex, CharSequence input) {
        return Pattern.compile(regex).matcher(input).matches();
    }

    /**
     * 判断邮箱
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isEmail(String email) {
        return valid(isEamil, email);
    }

    /**
     * 判断手机
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isMobile(String mobile) {
        return valid(isPhoneNumber, mobile);
    }

    /**
     * 判断设备号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isImei(String imei) {
        return valid(isImei, imei);
    }

    /**
     * 判断生日(yyyy-mm-dd)
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isBirthday(String birthday) {
        return valid(isBirthday, birthday);
    }

    /**
     * 判断网址
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isHttpUrl(String httpUrl) {
        return valid(isHttpUrl, httpUrl);
    }

    /**
     * 判断非0的正整数
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isPositiveInteger(String positiveInteger) {
        return valid(isPositiveInteger, positiveInteger);
    }

    /**
     * 判断是否为汉字
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isChinese(String chinese) {
        return valid(isChinese, chinese);
    }

    /**
     * 判断身份证
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isIDCard(String idCard) {
        return IDCardVaildUtil.isIDCard(idCard);
    }

    /**
     * 判断是否是ip地址
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isIP(String ip) {
        return valid(isIP, ip);
    }

}
