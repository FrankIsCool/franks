package com.franks.util.valid;

import com.franks.util.idCard.IDCardUtil;

import java.util.regex.Pattern;

/**
 * 数据格式校验
 *
 * @author frank
 * @date 2021/9/18 11:16
 */
public class ValidUtils {

    // 验证网址Url
    public static final String isHttpUrl = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    //验证邮箱
    public static final String isEamil = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    // 验证设备号
    public static final String isImei = "^[0-9A-Za-z]{14,70}$";
    // 验证用户的生日(yyyy-mm-dd)格式
    public static final String isBirthday = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
    // 只能输入非零的正整数
    public static final String isPositiveInteger = "^\\+?[1-9][0-9]*$";
    //判断是否为汉字
    public static final String isChinese = "^[\u4e00-\u9fa5]+$";
    //判断是否ip地址
    public static final String isIP = "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)";
    //微信支付流水号规则
    public static final String isWxPaySn = "([0-9A-Za-z]|[\\-\\_\\|\\*]){1,32}";
    //微信退费流水号规则
    public static final String isWxRefundSn = "([0-9A-Za-z]|[\\-\\_\\|\\*\\@]){1,64}";
    //支付宝支付流水号规则
    public static final String isALIPaySn = "([0-9A-Za-z]|[\\_]){1,64}";
    //京东支付流水号规则
    public static final String isJDPaySn = "([0-9A-Za-z]){1,32}";
    //建行支付流水号规则
    public static final String isCBPaySn = "([0-9A-Za-z]|[\\_]){1,30}";
    /**
     * 判断建行支付流水号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isCBPaySn(String paySn) {
        return valid(isCBPaySn, paySn);
    }
    /**
     * 判断微信支付流水号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isWxPaySn(String paySn) {
        return valid(isWxPaySn, paySn);
    }
    /**
     * 判断微信退费流水号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isWxRefundSn(String refundSn) {
        return valid(isWxRefundSn, refundSn);
    }
    /**
     * 判断支付宝支付流水号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isALIPaySn(String paySn) {
        return valid(isALIPaySn, paySn);
    }
    /**
     * 判断支付宝退费流水号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isALIRefundSn(String refundSn) {
        return valid(isALIPaySn, refundSn);
    }
    /**
     * 判断京东支付流水号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isJDPaySn(String paySn) {
        return valid(isJDPaySn, paySn);
    }

    /**
     * 判断京东退费流水号
     *
     * @Author frank
     * @Date 2021/9/18 11:20
     */
    public static boolean isJDRefundSn(String refundSn) {
        return valid(isJDPaySn, refundSn);
    }



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
        return IDCardUtil.isIDCard(idCard);
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
