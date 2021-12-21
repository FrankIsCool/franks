
package com.franks.util.crypt.util;


import com.franks.util.constant.Constant;

/**
 * RSA加密
 * SHA1WithRSA
 *
 * @author frank
 * @date 2021/9/19 15:30
 */
public class RSA extends RSABase {
    /**
     * RSA签名
     *
     * @param content           待签名数据
     * @param privateKey        私钥
     * @param characterEncoding 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String characterEncoding) {
        return sign(content, privateKey, SIGN_SHA1RSA_ALGORITHMS, characterEncoding);
    }

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String sign(String content, String privateKey) {
        return sign(content, privateKey, Constant.CHARSET);
    }

    /**
     * RSA验签名检查
     *
     * @param content           待签名数据
     * @param sign              签名值
     * @param publicKey         公钥
     * @param characterEncoding 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String publicKey, String characterEncoding) {
        return verify(content, sign, publicKey, SIGN_SHA1RSA_ALGORITHMS, characterEncoding);
    }

    /**
     * RSA验签名检查
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String publicKey) {
        return verify(content, sign, publicKey, SIGN_SHA1RSA_ALGORITHMS, Constant.CHARSET);
    }
}
