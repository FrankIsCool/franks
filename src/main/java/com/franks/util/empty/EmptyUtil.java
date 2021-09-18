package com.franks.util.empty;

import com.franks.util.exception.ApiException;
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
        if (o instanceof Integer || o instanceof Long) {
            return o != null;
        }
        if (o instanceof String) {
            return StringUtils.isNotBlank((String) o);
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

    public static void assertEmpty(Object o, String msg) {
        if (!isNotEmpty(o)) {
            throw new ApiException(msg);
        }
    }

}