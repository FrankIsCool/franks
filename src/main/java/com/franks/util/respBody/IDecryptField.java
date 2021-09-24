package com.franks.util.respBody;

/**
 * 解密接口
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
public interface IDecryptField {
    /**
     * 解密
     *
     * @param content 加密数据
     * @return 解密后数据
     */
    String decrypt(String content);
}