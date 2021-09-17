package com.franks.util.empty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基础数据校验
 *
 * @author frank
 * @module
 * @date 2021/9/4 13:51
 */
public class EmptyUtil {

    public static final String isPhoneNumber = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}"; // 手机号码正则    

    public static final String isHttpUrl = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?"; // 验证网址Url  

    public static final String isEamil = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"; //验证邮箱

    public static final String isImei = "^[0-9A-Za-z]{14,70}$"; // 验证设备号

    public static final String isPassword = "^[0-9a-zA-Z]{6,12}$"; // 验证密码必须是6-12位字符(数字和字母)

    public static final String isNikeName = "^[a-zA-Z0-9_\u4e00-\u9fa5]+${6,12}$"; // 用户呢称6-12位正则(只含有汉字、数字、字母、下划线，下划线位置不限)

    public static final String isBirthday = "\\d{4}-\\d{2}-\\d{2}"; // 验证用户的生日(yyyy-mm-dd)格式

    public static final String isPositiveInteger = "^\\+?[1-9][0-9]*$"; // 只能输入非零的正整数

    public static final String isChinese = "^[\u4e00-\u9fa5]+$"; //判断是否为汉字

    public static final String isIDCard = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$"; //判断身份证

    public static final String isIP = "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)"; //判断是否ip地址

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        isEmpty(list);
    }

    public static boolean isEmpty(Object o) {
        return null == o;
    }

    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        }
        str = str.trim();
        if ("null".equalsIgnoreCase(str)) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return str.length() > 0;
    }

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }


    // 判断邮箱
    public static boolean isEmail(String email) {
        String str = isEamil;
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //判断手机
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile(isPhoneNumber);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    //判断设备号
    public static boolean isImei(String imei) {
        Pattern p = Pattern.compile(isImei);
        Matcher m = p.matcher(imei);
        return m.matches();
    }

    //判断生日(yyyy-mm-dd)
    public static boolean isBirthday(String birthday) {
        Pattern p = Pattern.compile(isBirthday);
        Matcher m = p.matcher(birthday);
        return m.matches();
    }

    //判断网址
    public static boolean isHttpUrl(String httpUrl) {
        Pattern p = Pattern.compile(isHttpUrl);
        Matcher m = p.matcher(httpUrl);
        return m.matches();
    }

    //判断非0的正整数
    public static boolean isPositiveInteger(String positiveInteger) {
        Pattern p = Pattern.compile(isPositiveInteger);
        Matcher m = p.matcher(positiveInteger);
        return m.matches();
    }


    //判断是否为汉字
    public static boolean isChinese(String chinese) {
        Pattern p = Pattern.compile(isChinese);
        Matcher m = p.matcher(chinese);
        return m.matches();
    }


    //判断身份证
    public static boolean isIDCard(String idCard) {
        Pattern p = Pattern.compile(isIDCard);
        Matcher m = p.matcher(idCard);
        return m.matches();
    }


    //判断是否是ip地址
    public static boolean isIP(String ip) {
        Pattern p = Pattern.compile(isIP);
        Matcher m = p.matcher(ip);
        return m.matches();
    }
}