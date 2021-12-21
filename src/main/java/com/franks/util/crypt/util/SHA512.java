package com.franks.util.crypt.util;

import com.franks.util.exception.ApiException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * SHA512签名
 *
 * @author frank
 * @date 2021/9/19 15:32
 */
public class SHA512 {

    /**
     * 签名字符串
     *
     * @param text              需要签名的字符串
     * @param key               密钥
     * @param characterEncoding 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String characterEncoding) {
        //拼接key
        try {
            return DigestUtils.sha512Hex(text + key.getBytes(characterEncoding));
        } catch (UnsupportedEncodingException e) {
            throw new ApiException(e);
        }
    }


    /**
     * 签名字符串
     *
     * @param text              需要签名的字符串
     * @param sign              签名结果
     * @param key               密钥
     * @param characterEncoding 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String characterEncoding) {
        //判断是否一样
        return sign(text, key, characterEncoding).toUpperCase().equals(sign.toUpperCase());
    }

}
