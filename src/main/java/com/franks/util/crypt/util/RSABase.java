
package com.franks.util.crypt.util;


import com.franks.util.constant.Constant;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密
 * SHA1WithRSA
 *
 * @author frank
 * @date 2021/9/19 15:30
 */
public class RSABase {

    private static final String ALGORITHM = "RSA";

    public static final String SIGN_SHA1RSA_ALGORITHMS = "SHA1WithRSA";

    public static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    /**
     * 获取私钥
     *
     * @param privateKey 私钥
     * @return java.security.PrivateKey 私钥
     * @Author frank
     * @Date 2021/12/20 17:55
     */
    private static PrivateKey getPrivate(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(privateKey)));
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥
     * @return java.security.PublicKey 公钥
     * @Author frank
     * @Date 2021/12/20 17:55
     */
    private static PublicKey getPublic(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(publicKey)));
    }

    /**
     * RSA签名
     *
     * @param content           待签名数据
     * @param privateKey        私钥
     * @param signAlgorithms    签名算法
     * @param characterEncoding 编码格式
     * @return 签名值
     */
    protected static String sign(String content, String privateKey, String signAlgorithms, String characterEncoding) {
        try {
            return sign(content, getPrivate(privateKey), signAlgorithms, characterEncoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * RSA签名
     *
     * @param content           待签名数据
     * @param privateKey        私钥
     * @param signAlgorithms    签名算法
     * @param characterEncoding 编码格式
     * @return 签名值
     */
    protected static String sign(String content, PrivateKey privateKey, String signAlgorithms, String characterEncoding) {
        try {
            java.security.Signature signature = java.security.Signature.getInstance(signAlgorithms);
            signature.initSign(privateKey);
            signature.update(content.getBytes(characterEncoding));
            byte[] signed = signature.sign();
            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content           待签名数据
     * @param sign              签名值
     * @param publicKey         公钥
     * @param signAlgorithms    签名算法
     * @param characterEncoding 编码格式
     * @return 布尔值
     */
    protected static boolean verify(String content, String sign, String publicKey, String signAlgorithms, String characterEncoding) {
        try {
            return verify(content, sign, getPublic(publicKey), signAlgorithms, characterEncoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * RSA验签名检查
     *
     * @param content           待签名数据
     * @param sign              签名值
     * @param publicKey         公钥
     * @param signAlgorithms    签名算法
     * @param characterEncoding 编码格式
     * @return 布尔值
     */
    protected static boolean verify(String content, String sign, PublicKey publicKey, String signAlgorithms, String characterEncoding) {
        try {
            java.security.Signature signature = java.security.Signature.getInstance(signAlgorithms);
            signature.initVerify(publicKey);
            signature.update(content.getBytes(characterEncoding));
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * RSA公钥加密
     *
     * @param content   加密字符串
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String content, String publicKey) {
        return encrypt(content, publicKey,Constant.CHARSET);
    }
    /**
     * RSA公钥加密
     *
     * @param content   加密字符串
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String content, String publicKey,String characterEncoding) {
        try {
            return encrypt(content, getPublic(publicKey),characterEncoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * RSA公钥加密
     *
     * @param content   加密字符串
     * @param publicKey 公钥
     * @return 密文
     */
    protected static String encrypt(String content, PublicKey publicKey,String characterEncoding) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(characterEncoding)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA私钥解密
     *
     * @param encrypt    待解密字符串
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decrypt(String encrypt, String privateKey) {
        return decrypt(encrypt, privateKey,Constant.CHARSET);
    }
    /**
     * RSA私钥解密
     *
     * @param encrypt    待解密字符串
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decrypt(String encrypt, String privateKey,String characterEncoding) {
        try {
            return decrypt(encrypt, getPrivate(privateKey),characterEncoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * RSA私钥解密
     *
     * @param encrypt    待解密字符串
     * @param privateKey 私钥
     * @return 明文
     */
    protected static String decrypt(String encrypt, PrivateKey privateKey,String characterEncoding) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64.decode(encrypt)), Charset.forName(characterEncoding));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
