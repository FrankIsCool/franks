package com.franks.util.crypt.util;

import com.franks.util.constant.Constant;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

public class DES {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /**
     * 生成key
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key generateKey(String key) throws Exception {
        return SecretKeyFactory.getInstance(ALGORITHM).generateSecret(new DESKeySpec(key.getBytes(Constant.CHARSET)));
    }


    /**
     * DES加密字符串
     *
     * @param key     加密密码
     * @param content 待加密字符串
     * @return 加密后内容
     */
    public static String encrypt(String content, String key) {
        return encrypt(content, key, Constant.CHARSET);
    }

    /**
     * DES加密字符串
     *
     * @param key     加密密码
     * @param content 待加密字符串
     * @return 加密后内容
     */
    public static String encrypt(String content, String key, String characterEncoding) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
            return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(characterEncoding)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密字符串
     *
     * @param key     解密密码
     * @param encrypt 待解密字符串
     * @return 解密后内容
     */
    public static String decrypt(String encrypt, String key) {
        return encrypt(encrypt, key, Constant.CHARSET);
    }

    /**
     * DES解密字符串
     *
     * @param key     解密密码
     * @param encrypt 待解密字符串
     * @return 解密后内容
     */
    public static String decrypt(String encrypt, String key, String characterEncoding) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
            return new String(cipher.doFinal(Base64.decode(encrypt)), Charset.forName(characterEncoding));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
