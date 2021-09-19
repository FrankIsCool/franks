package com.franks.util.transition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * 转对象工具类
 *
 * @module
 * @author frank
 * @date 2021/9/19 12:48
 */
public class ObjectUtil<T> {
    /**
     * 字符串转对象
     *
     * @param str    json字符串
     * @param classs 被转换成的对象类
     * @return T
     * @Author frank
     * @Date 2021/9/19 12:45
     */
    public static <T> T toBean(String str, Class<T> classs) {
        return JSONObject.parseObject(str, classs);
    }

    /**
     * jsonObject转对象
     *
     * @param jsonObject json
     * @param classs     被转换成的对象类
     * @return T
     * @Author frank
     * @Date 2021/9/19 12:45
     */
    public static <T> T toBean(JSONObject jsonObject, Class<T> classs) {
        return toBean(jsonObject.toJSONString(), classs);
    }

    /**
     * map转对象
     *
     * @param map    map
     * @param classs 被转换成的对象类
     * @return T
     * @Author frank
     * @Date 2021/9/19 12:45
     */
    public static <T> T toBean(Map map, Class<T> classs) {
        return toBean(JSONObject.toJSONString(map), classs);
    }

    /**
     * map转对象-对象嵌套泛型对象
     *
     * @param map map
     * @return T
     * @Author frank
     * @Date 2021/9/19 12:45
     */
    public static <T> T toBean(Map map) {
        return toBean(JSONObject.toJSONString(map));
    }

    /**
     * jsonObject转对象-对象嵌套泛型对象
     *
     * @param jsonObject json
     * @return T
     * @Author frank
     * @Date 2021/9/19 12:45
     */
    public static <T> T toBean(JSONObject jsonObject) {
        return toBean(jsonObject.toJSONString());
    }

    /**
     * 字符串转对象-对象嵌套泛型对象
     *
     * @param str json字符串
     * @return T
     * @Author frank
     * @Date 2021/9/19 12:45
     */
    public static <T> T toBean(String str) {
        return JSON.parseObject(str, new TypeReference<T>() {
        });
    }
}
