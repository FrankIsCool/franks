package com.franks.util.crypt.util;

import com.franks.util.empty.EmptyUtil;

import java.util.*;
import java.util.stream.Collectors;

import static com.franks.util.constant.Constant.*;

public class ParamUtil {


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
        if (EmptyUtil.isEmpty(parameters) || EmptyUtil.isEmpty(ignoreKey)) {
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
                    parameters.put(key, Arrays.asList(values).stream().filter(value -> EmptyUtil.isNotEmpty(value)).collect(Collectors.joining(SEPARATOR_COMMA)));
                }
            }
        });
        return parameters;
    }

    /**
     * 拼接字符串-格式：key=value[separator]key=value
     *
     * @param parameters 待处理数据
     * @param separator  数据分隔符
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/19 11:22
     */
    private static String paramToStr(Map parameters, String separator) {
        if (EmptyUtil.isEmpty(parameters)) {
            return BLANK_STR;
        }
        StringBuffer sb = new StringBuffer();
        //已经排序好处理
        if (parameters instanceof SortedMap) {
            parameters.keySet().stream().forEach(key -> {
                sb.append(key).append("=").append(parameters.get(key).toString().trim()).append(separator);
            });
            if (EmptyUtil.isNotEmpty(sb, separator)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        //未排序须处理
        List<String> keys = new ArrayList<>(parameters.keySet());
        //排序
        Collections.sort(keys);
        keys.stream().forEach(key -> {
            sb.append(key).append("=").append(parameters.get(key)).append(separator);
        });
        if (EmptyUtil.isNotEmpty(sb)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 拼接字符串-格式：value[separator]value
     *
     * @param parameters 待处理数据
     * @param separator  数据分隔符
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/19 11:22
     */
    private static String paramValueToStr(Map parameters, String separator) {
        if (EmptyUtil.isEmpty(parameters)) {
            return BLANK_STR;
        }
        StringBuffer sb = new StringBuffer();
        //已经排序好处理
        if (parameters instanceof SortedMap) {
            parameters.keySet().stream().forEach(key -> {
                sb.append(parameters.get(key).toString().trim()).append(separator);
            });
            if (EmptyUtil.isNotEmpty(sb, separator)) {
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
        if (EmptyUtil.isNotEmpty(sb)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 拼接字符串-key[separator]key
     *
     * @param parameters 待处理数据
     * @param separator  数据分隔符
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/19 11:22
     */
    private static String paramKeyToStr(Map parameters, String separator) {
        if (EmptyUtil.isEmpty(parameters)) {
            return BLANK_STR;
        }
        StringBuffer sb = new StringBuffer();
        //已经排序好处理
        if (parameters instanceof SortedMap) {
            parameters.keySet().stream().forEach(key -> {
                sb.append(key).append(separator);
            });
            if (EmptyUtil.isNotEmpty(sb, separator)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        //未排序须处理
        List<String> keys = new ArrayList<>(parameters.keySet());
        //排序
        Collections.sort(keys);
        keys.stream().forEach(key -> {
            sb.append(key).append(separator);
        });
        if (EmptyUtil.isNotEmpty(sb)) {
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
     * @param inNull     是否包含空值
     * @param ignoreKey  需要忽略添加的key
     * @return 新签名
     */
    private static Map parameterText(Map parameters, Boolean inNull, String... ignoreKey) {
        if (EmptyUtil.isEmpty(parameters)) {
            return null;
        }
        if (inNull) {
            return paramValues(paramIgnoreKey(parameters, ignoreKey));
        }
        return paramValues(paramIgnoreNull(paramIgnoreKey(parameters, ignoreKey)));
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
        return paramToStr(parameterText(parameters,inNull,ignoreKey), separator);
    }


    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterText(Map parameters) {
        return parameterText(parameters, AND);

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
    public static String parameterText(Map parameters, String separator, Boolean inNull) {
        return parameterText(parameters, separator, inNull, SIGNATURE, SIGN, KEY, SIGN_TYPE,SIGNTYPE);
    }

    /**
     * 把数组所有元素排序，并按照“参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @param inNull     是否包含空值
     * @param ignoreKey  需要忽略添加的key
     * @return 新签名
     */
    public static String parameterValueText(Map parameters, String separator, Boolean inNull, String... ignoreKey) {
        return paramValueToStr(parameterText(parameters,inNull,ignoreKey), separator);
    }
    /**
     * 把数组所有元素排序，并按照“参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterValueText(Map parameters) {
        return parameterValueText(parameters, AND);

    }

    /**
     * 把数组所有元素排序，并按照“参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterValueText(Map parameters, String separator) {
        return parameterValueText(parameters, separator, Boolean.FALSE);
    }

    /**
     * 把数组所有元素排序，并按照“参数值”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterValueText(Map parameters, String separator, Boolean inNull) {
        return parameterValueText(parameters, separator, inNull, SIGNATURE, SIGN, KEY, SIGN_TYPE,SIGNTYPE);
    }
    /**
     * 把数组所有元素排序，并按照“参数”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @param inNull     是否包含空值
     * @param ignoreKey  需要忽略添加的key
     * @return 新签名
     */
    public static String parameterKeyText(Map parameters, String separator, Boolean inNull, String... ignoreKey) {
        return paramKeyToStr(parameterText(parameters,inNull,ignoreKey), separator);
    }
    /**
     * 把数组所有元素排序，并按照“参数”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterKeyText(Map parameters) {
        return parameterKeyText(parameters, AND);

    }

    /**
     * 把数组所有元素排序，并按照“参数”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterKeyText(Map parameters, String separator) {
        return parameterKeyText(parameters, separator, Boolean.FALSE);
    }

    /**
     * 把数组所有元素排序，并按照“参数”的模式用“@param separator”字符拼接成字符串
     *
     * @param parameters 参数
     * @param separator  分隔符
     * @return 去掉空值与签名参数后的新签名，拼接后字符串
     */
    public static String parameterKeyText(Map parameters, String separator, Boolean inNull) {
        return parameterKeyText(parameters, separator, inNull, SIGNATURE, SIGN, KEY, SIGN_TYPE,SIGNTYPE);
    }
}
