package com.franks.util.empty;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 空校验
 *
 * @author frank
 * @module
 * @date 2021/9/4 13:51
 */
public class EmptyUtil {
    /**
     * 非空校验
     *
     * @param o 待校验数据
     * @return 是否为空
     * @Author frank
     * @Date 2021/9/18 11:18
     */
    public static boolean isNotEmpty(Object o) {
        if (o instanceof Number) {
            return o != null;
        }
        if (o instanceof CharSequence) {
            return StringUtils.isNotBlank((CharSequence) o);
        }
        if (o instanceof Collection) {
            return CollectionUtils.isNotEmpty((Collection) o);
        }
        if (o instanceof Map) {
            return MapUtils.isNotEmpty((Map<Object, Object>) o);
        }
        return null != o;
    }

    public static boolean isEmpty(Object o) {
        return !isNotEmpty(o);
    }

    /**
     * 如果都不为空，则为true
     *
     * @param css 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isNotEmpty(CharSequence... css) {
        return StringUtils.isNoneBlank(css);
    }

    /**
     * 如果其中有一个为空，则为true
     *
     * @param css 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isEmpty(CharSequence... css) {
        return StringUtils.isAnyBlank(css);
    }

    /**
     * 如果都不为空，则为true
     *
     * @param num 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isNotEmpty(Number... num) {
        return !isEmpty(num);
    }

    /**
     * 如果其中有一个为空，则为true
     *
     * @param num 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isEmpty(Number... num) {
        if (null == num || num.length < 1) {
            return Boolean.TRUE;
        }
        for (int i = 0; i < num.length; i++) {
            if (null == num[i]) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 如果都不为空，则为true
     *
     * @param col 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isNotEmpty(Collection... col) {
        return !isEmpty(col);
    }

    /**
     * 如果其中有一个为空，则为true
     *
     * @param col 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isEmpty(Collection... col) {
        if (null == col || col.length < 1) {
            return Boolean.TRUE;
        }
        for (int i = 0; i < col.length; i++) {
            if (null == col[i] || CollectionUtils.isEmpty(col[i])) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 如果都不为空，则为true
     *
     * @param map 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isNotEmpty(Map... map) {
        return !isEmpty(map);
    }

    /**
     * 如果其中有一个为空，则为true
     *
     * @param map 待校验参数
     * @return
     * @Author frank
     * @Date 2021/9/27 13:53
     */
    public static boolean isEmpty(Map... map) {
        if (null == map || map.length < 1) {
            return Boolean.TRUE;
        }
        for (int i = 0; i < map.length; i++) {
            if (null == map[i] || MapUtils.isEmpty(map[i])) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}