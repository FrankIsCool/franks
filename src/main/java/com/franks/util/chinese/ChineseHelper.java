package com.franks.util.chinese;

import com.franks.util.empty.EmptyUtil;
import com.franks.util.resource.SysResource;

import java.util.Map;

/**
 * 简繁体转换
 *
 * @author frank
 * @date 2021/9/30 16:37
 */
public class ChineseHelper {

    private static final Map<String, String> SIMPLIFIED = SysResource.getResource("/chinese/ToSimplified.dict");

    private static final Map<String, String> TRADITIONAL = SysResource.getResource("/chinese/ToTraditional.dict");

    private static class ChineseHelperHolder {
        private static final ChineseHelper instance = new ChineseHelper();
    }

    public static ChineseHelper getInStance() {
        return ChineseHelper.ChineseHelperHolder.instance;
    }

    /**
     * 中文-转简体字
     *
     * @param traditional 字符串
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/30 15:26
     */
    public String toSimplified(String traditional) {
        return to(traditional, SIMPLIFIED);
    }

    /**
     * 中文-转换简繁体
     *
     * @param str 字符串
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/30 15:26
     */
    private String to(String str, Map<String, String> map) {
        if (EmptyUtil.isEmpty(str)) {
            return str;
        }
        char[] chars = str.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        Integer index = 0;
        String cha = null;
        while (index < chars.length) {
            String simplifiedChinese = map.get(cha = String.valueOf(chars[index]));
            sb.append(simplifiedChinese != null ? simplifiedChinese : cha);
            index++;
        }
        return sb.toString();
    }

    /**
     * 中文-转繁体字
     *
     * @param simplified 简体字
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/30 15:26
     */
    public String toTraditional(String simplified) {
        return to(simplified, TRADITIONAL);
    }
}
