package com.franks.util.sign;


import com.franks.util.empty.EmptyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 加密工具
 *
 * @author frank
 * @date 2021/9/19 15:30
 */
public enum SignUtils {

    MD5 {
        @Override
        public String createSign(String content, String key, String characterEncoding) {

            return com.franks.util.sign.MD5.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String key, String characterEncoding) {
            return com.franks.util.sign.MD5.verify(text, sign, key, characterEncoding);
        }

        @Override
        public String decrypt(String sign, String key, String characterEncoding) {
            return null;
        }
    },

    RSA {
        @Override
        public String createSign(String content, String key, String characterEncoding) {
            return com.franks.util.sign.RSA.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.sign.RSA.verify(text, sign, publicKey, characterEncoding);
        }

        @Override
        public String decrypt(String sign, String key, String characterEncoding) {
            return com.franks.util.sign.RSA.decrypt(sign, key, characterEncoding);
        }
    },

    RSA2 {
        @Override
        public String createSign(String content, String key, String characterEncoding) {
            return com.franks.util.sign.RSA2.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.sign.RSA2.verify(text, sign, publicKey, characterEncoding);
        }

        @Override
        public String decrypt(String sign, String key, String characterEncoding) {
            return com.franks.util.sign.RSA2.decrypt(sign, key, characterEncoding);
        }
    },
    SHA1 {
        @Override
        public String createSign(String content, String key, String characterEncoding) {
            return com.franks.util.sign.SHA1.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.sign.SHA1.verify(text, sign, publicKey, characterEncoding);
        }

        @Override
        public String decrypt(String sign, String key, String characterEncoding) {
            return null;
        }
    },
    SHA256 {
        @Override
        public String createSign(String content, String key, String characterEncoding) {
            return com.franks.util.sign.SHA256.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.sign.SHA256.verify(text, sign, publicKey, characterEncoding);
        }

        @Override
        public String decrypt(String sign, String key, String characterEncoding) {
            return null;
        }
    },
    SM3 {
        @Override
        public String createSign(String content, String key, String characterEncoding) {
            return com.franks.util.sign.RSA2.sign(content, key, characterEncoding);
        }

        @Override
        public boolean verify(String text, String sign, String publicKey, String characterEncoding) {
            return com.franks.util.sign.RSA2.verify(text, sign, publicKey, characterEncoding);
        }

        @Override
        public String decrypt(String sign, String key, String characterEncoding) {
            return com.franks.util.sign.RSA2.decrypt(sign, key, characterEncoding);
        }
    };

    protected static final Log log = LogFactory.getLog(SignUtils.class);

    /**
     * 加密
     *
     * @param content           需要签名的内容
     * @param key               密钥
     * @param characterEncoding 字符编码
     * @return 签名值
     */
    public abstract String createSign(String content, String key, String characterEncoding);

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
     * 解密
     *
     * @param sign              密文
     * @param key               密钥
     * @param characterEncoding 编码格式
     * @return 签名结果
     */
    public abstract String decrypt(String sign, String key, String characterEncoding);
    /**
     * 根据条件移除不参与加密的key
     *
     * @param parameters 待处理数据
     * @param ignoreKey  需要忽略的字段
     * @return java.util.Map
     * @Author frank
     * @Date 2021/9/19 10:13
     */
    private static Map paramIgnoreKey(Map parameters, String... ignoreKey) {
        if (EmptyUtil.isEmpty(parameters) || EmptyUtil.isEmpty(ignoreKey) || ignoreKey.length < 1) {
            return parameters;
        }
        Arrays.asList(ignoreKey).stream().forEach(key -> {
            if (EmptyUtil.isNotEmpty(key) && parameters.containsKey(key)) {
                parameters.remove(key);
            }
        });
        return parameters;
    }

    /**
     * 删除value值为空的数据
     *
     * @param parameters 待处理的数据
     * @return java.util.Map
     * @Author frank
     * @Date 2021/9/19 10:13
     */
    private static Map paramIgnoreNull(Map parameters) {
        if (EmptyUtil.isEmpty(parameters)) {
            return parameters;
        }
        Set set = parameters.keySet();
        set.stream().forEach(key -> {
            Object o = parameters.get(key);
            if (EmptyUtil.isEmpty(o) || EmptyUtil.isEmpty(o.toString())) {
                parameters.remove(key);
            }
        });
        return parameters;
    }

    /**
     * 处理值为数组的数据
     *
     * @param parameters 待处理的数据
     * @return java.util.Map
     * @Author frank
     * @Date 2021/9/19 10:13
     */
    private static Map paramValues(Map parameters) {
        if (EmptyUtil.isEmpty(parameters)) {
            return parameters;
        }
        Set set = parameters.keySet();
        set.stream().forEach(key -> {
            Object o = parameters.get(key);
            if (o instanceof String[]) {
                String[] values = (String[]) o;
                if (EmptyUtil.isNotEmpty(values)) {
                    parameters.put(key, Arrays.asList(values).stream().filter(value -> EmptyUtil.isNotEmpty(value)).collect(Collectors.joining(",")));
                }
            }
        });
        return parameters;
    }

    /**
     * 拼接字符串
     *
     * @param parameters 待处理数据
     * @param separator  数据分隔符
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/19 11:22
     */
    private static String paramToStr(Map parameters, String separator) {
        if (EmptyUtil.isEmpty(parameters)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        //已经排序好处理
        if (parameters instanceof SortedMap) {
            parameters.keySet().stream().forEach(key -> {
                sb.append(key).append("=").append(parameters.get(key).toString().trim()).append(separator);
            });
            if (sb.length() > 0 && !"".equals(separator)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        //未排序须处理
        List<String> keys = new ArrayList<String>(parameters.keySet());
        //排序
        Collections.sort(keys);
        keys.stream().forEach(key -> {
            sb.append(key).append("=").append(parameters.get(key)).append(separator);
        });
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    /**
     * 拼接字符串-数据的value
     *
     * @param parameters 待处理数据
     * @param separator  数据分隔符
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/19 11:22
     */
    private static String paramValueToStr(Map parameters, String separator) {
        if (EmptyUtil.isEmpty(parameters)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        //已经排序好处理
        if (parameters instanceof SortedMap) {
            parameters.keySet().stream().forEach(key -> {
                sb.append(parameters.get(key).toString().trim()).append(separator);
            });
            if (sb.length() > 0 && !"".equals(separator)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        //未排序须处理
        List<String> keys = new ArrayList<String>(parameters.keySet());
        //排序
        Collections.sort(keys);
        keys.stream().forEach(key -> {
            sb.append(parameters.get(key)).append(separator);
        });
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    /**
     * 拼接字符串-数据的key
     *
     * @param parameters 待处理数据
     * @param separator  数据分隔符
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/19 11:22
     */
    private static String paramKeyToStr(Map parameters, String separator) {
        if (EmptyUtil.isEmpty(parameters)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        //已经排序好处理
        if (parameters instanceof SortedMap) {
            parameters.keySet().stream().forEach(key -> {
                sb.append(key).append(separator);
            });
            if (sb.length() > 0 && !"".equals(separator)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        //未排序须处理
        List<String> keys = new ArrayList<String>(parameters.keySet());
        //排序
        Collections.sort(keys);
        keys.stream().forEach(key -> {
            sb.append(key).append(separator);
        });
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    /**
     * 拼接字符串
     * 示例：参数=参数值[separator]参数=参数值
     * 空值不计算在内
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @param inNull     是否包含空值
     * @param ignoreKey  需要忽略添加的key
     * @return 新签名
     */
    public static String parameterText(Map parameters, String separator, Boolean inNull, String... ignoreKey) {
        if (EmptyUtil.isEmpty(separator)) {
            separator = "";
        }
        if (EmptyUtil.isEmpty(parameters)) {
            return "";
        }
        if (inNull) {
            return paramToStr(paramValues(paramIgnoreKey(parameters, ignoreKey)), separator);
        }
        return paramToStr(paramValues(paramIgnoreNull(paramIgnoreKey(parameters, ignoreKey))), separator);
    }
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterText(Map parameters) {
        return parameterText(parameters, "&");

    }
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterText(Map parameters, String separator) {
        return parameterText(parameters, separator, Boolean.FALSE);
    }
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterText(Map parameters, String separator,Boolean inNull) {
        return parameterText(parameters, separator, inNull,"signature", "sign", "key", "sign_type");
    }
    /**
     * 拼接字符串
     * 示例：参数值[separator]参数值
     * 空值计算在内
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @param inNull     是否包含空值
     * @param ignoreKey  需要忽略添加的key
     * @return 新签名
     */
    public static String parameterValueText(Map parameters, String separator, Boolean inNull, String... ignoreKey) {
        if (EmptyUtil.isEmpty(separator)) {
            separator = "";
        }
        if (EmptyUtil.isEmpty(parameters)) {
            return "";
        }
        if (inNull) {
            return paramValueToStr(paramValues(paramIgnoreKey(parameters, ignoreKey)), separator);
        }
        return paramValueToStr(paramValues(paramIgnoreNull(paramIgnoreKey(parameters, ignoreKey))), separator);
    }
    /**
     * 拼接字符串
     * 示例：参数值[separator]参数值
     * 空值计算在内
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @param inNull     是否包含空值
     * @param ignoreKey  需要忽略添加的key
     * @return 新签名
     */
    public static String parameterKeyText(Map parameters, String separator, Boolean inNull, String... ignoreKey) {
        if (EmptyUtil.isEmpty(separator)) {
            separator = "";
        }
        if (EmptyUtil.isEmpty(parameters)) {
            return "";
        }
        if (inNull) {
            return paramKeyToStr(paramValues(paramIgnoreKey(parameters, ignoreKey)), separator);
        }
        return paramKeyToStr(paramValues(paramIgnoreNull(paramIgnoreKey(parameters, ignoreKey))), separator);
    }
    /**
     * 签名
     *
     * @param parameters        需要进行排序签名的参数
     * @param key               密钥
     * @param characterEncoding 编码格式
     * @return 签名值
     */
    public String sign(Map parameters, String key, String characterEncoding) {
        return sign(parameters, "&", key, characterEncoding);
    }

    /**
     * 签名
     *
     * @param parameters        需要进行排序签名的参数
     * @param key               密钥
     * @param separator         分隔符  默认 &amp;
     * @param characterEncoding 编码格式
     * @return 签名值
     */
    public String sign(Map parameters, String key, String separator, String characterEncoding) {
        return createSign(parameterText(parameters, separator), key, characterEncoding);
    }

    /**
     * 签名字符串
     *
     * @param params            需要签名的字符串
     * @param sign              签名结果
     * @param key               密钥
     * @param characterEncoding 编码格式
     * @return 签名结果
     */
    public boolean verify(Map params, String sign, String key, String characterEncoding) {
        //判断是否一样
        return this.verify(parameterText(params), sign, key, characterEncoding);
    }
}
