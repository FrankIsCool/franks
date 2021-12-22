package com.franks.util.crypt.enums;


import com.franks.util.crypt.util.Base64;

import java.nio.charset.Charset;

/**
 * 加密工具
 *
 * @author frank
 * @date 2021/9/19 15:30
 */
public enum EncryptEnums {
    BASE64 {
        @Override
        public String encrypt(String content, String key, String characterEncoding) {
            return Base64.encode((content + key).getBytes(Charset.forName(characterEncoding)));
        }

        @Override
        public String decrypt(String sign, String key, String characterEncoding) {
            return (new String(Base64.decode(sign), Charset.forName(characterEncoding)).split(key))[0];
        }
    },
    DES {
        @Override
        public String encrypt(String content, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.DES.encrypt(content,publicKey,characterEncoding);
        }

        @Override
        public String decrypt(String sign, String privateKey, String characterEncoding) {
            return com.franks.util.crypt.util.DES.decrypt(sign,privateKey,characterEncoding);
        }
    },
    RSA {
        @Override
        public String encrypt(String content, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.RSA.encrypt(content, publicKey,characterEncoding);
        }

        @Override
        public String decrypt(String sign, String privateKey, String characterEncoding) {
            return com.franks.util.crypt.util.RSA.decrypt(sign, privateKey,characterEncoding);
        }
    },
    SHA1 {
        @Override
        public String encrypt(String content, String publicKey, String characterEncoding) {
            return null;
        }

        @Override
        public String decrypt(String sign, String privateKey, String characterEncoding) {
            return null;
        }
    },
    SHA256 {
        @Override
        public String encrypt(String content, String publicKey, String characterEncoding) {
            return null;
        }

        @Override
        public String decrypt(String sign, String privateKey, String characterEncoding) {
            return null;
        }
    },
    SHA384 {
        @Override
        public String encrypt(String content, String publicKey, String characterEncoding) {
            return null;
        }

        @Override
        public String decrypt(String sign, String privateKey, String characterEncoding) {
            return null;
        }
    },
    SHA512 {
        @Override
        public String encrypt(String content, String publicKey, String characterEncoding) {
            return null;
        }

        @Override
        public String decrypt(String sign, String privateKey, String characterEncoding) {
            return null;
        }
    };
    /**
     * 加密
     *
     * @param content           内容
     * @param publicKey         公钥
     * @param characterEncoding 编码格式
     * @return 签名结果
     */
    public abstract String encrypt(String content, String publicKey, String characterEncoding);

    /**
     * 解密
     *
     * @param sign              密文
     * @param privateKey        私钥
     * @param characterEncoding 编码格式
     * @return 签名结果
     */
    public abstract String decrypt(String sign, String privateKey, String characterEncoding);

}
