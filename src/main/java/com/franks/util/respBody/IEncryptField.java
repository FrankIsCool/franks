package com.franks.util.respBody;

/**
 * 加密接口
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
public interface IEncryptField {
    /**
     * 加密
     *
     * @param content 需要签名的内容
     * @return 加密后数据
     */
    String encrypt(String content);
}