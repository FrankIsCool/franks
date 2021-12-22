package com.franks.util.crypt.enums;


import com.franks.util.crypt.util.*;

import java.nio.charset.Charset;
import java.util.Map;

import static com.franks.util.constant.Constant.AND;
import static com.franks.util.constant.Constant.CHARSET;

/**
 * 加密工具
 *
 * @author frank
 * @date 2021/9/19 15:30
 */
public enum SignEnums {
    BASE64 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return Base64.encode((content + key).getBytes(Charset.forName(characterEncoding)));
        }

        @Override
        public boolean verify(String text, String sign, String key, String characterEncoding) {
            return Base64.encode((text + key).getBytes(Charset.forName(characterEncoding))).equals(sign);
        }
    },
    MD2 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.MD2.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String key, String characterEncoding) {
            return com.franks.util.crypt.util.MD2.verify(text, sign, key, characterEncoding);
        }
    },
    MD5 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.MD5.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String key, String characterEncoding) {
            return com.franks.util.crypt.util.MD5.verify(text, sign, key, characterEncoding);
        }
    },
    RSA {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.RSA.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.RSA.verify(text, sign, publicKey, characterEncoding);
        }
    },
    RSA2 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.RSA2.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.RSA2.verify(text, sign, publicKey, characterEncoding);
        }
    },
    SHA1 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.SHA1.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.SHA1.verify(text, sign, publicKey, characterEncoding);
        }
    },
    SHA256 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.SHA256.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.SHA256.verify(text, sign, publicKey, characterEncoding);
        }
    },
    SHA384 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.SHA384.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.SHA384.verify(text, sign, publicKey, characterEncoding);
        }
    },
    SHA512 {
        @Override
        public String sign(String content, String key, String characterEncoding) {
            return com.franks.util.crypt.util.SHA512.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.crypt.util.SHA512.verify(text, sign, publicKey, characterEncoding);
        }
    };

    /**
     * 加密
     *
     * @param content           需要签名的内容
     * @param key               密钥
     * @param characterEncoding 字符编码
     * @return 签名值
     */
    public abstract String sign(String content, String key, String characterEncoding);

    /**
     * 验签
     *
     * @param text              需要签名的字符串
     * @param sign              密文
     * @param key               密钥
     * @param characterEncoding 编码格式
     * @return 签名结果
     */
    public abstract boolean verify(String text, String sign, String key, String characterEncoding);

    /**
     * 签名
     *
     * @param parameters 需要进行排序签名的参数
     * @param key        密钥
     * @return 签名值
     */
    public String sign(Map parameters, String key) {
        return sign(ParamUtil.parameterText(parameters, AND), key, CHARSET);
    }

    /**
     * 签名字符串
     *
     * @param params 需要签名的字符串
     * @param sign   签名结果
     * @param key    密钥
     * @return 签名结果
     */
    public boolean verify(Map params, String sign, String key) {
        return this.verify(ParamUtil.parameterText(params), sign, key, CHARSET);
    }

    /**
     * 签名
     *
     * @param parameters 需要进行排序签名的参数
     * @param key        密钥
     * @return 签名值
     */
    public String signValue(Map parameters, String key) {
        return sign(ParamUtil.parameterValueText(parameters, AND), key, CHARSET);
    }

    /**
     * 签名字符串
     *
     * @param params 需要签名的字符串
     * @param sign   签名结果
     * @param key    密钥
     * @return 签名结果
     */
    public boolean verifyValue(Map params, String sign, String key) {
        return this.verify(ParamUtil.parameterValueText(params), sign, key, CHARSET);
    }

    /**
     * 签名
     *
     * @param parameters 需要进行排序签名的参数
     * @param key        密钥
     * @return 签名值
     */
    public String signKey(Map parameters, String key) {
        return sign(ParamUtil.parameterKeyText(parameters, AND), key, CHARSET);
    }

    /**
     * 签名字符串
     *
     * @param params 需要签名的字符串
     * @param sign   签名结果
     * @param key    密钥
     * @return 签名结果
     */
    public boolean verifyKey(Map params, String sign, String key) {
        return this.verify(ParamUtil.parameterKeyText(params), sign, key, CHARSET);
    }
}
