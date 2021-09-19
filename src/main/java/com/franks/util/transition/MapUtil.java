package com.franks.util.transition;


import com.franks.util.exception.ApiException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 转map对象
 *
 * @author frank
 * @module
 * @date 2021/9/19 12:48
 */
public class MapUtil {

    /**
     * 对象转map-string
     *
     * @param
     * @return
     * @author frank(付帅)
     * @date 2021/4/13
     **/
    public static Map<String, String> toMapStr(Object object) {
        if (null == object) {
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Map<String, String> parm = new HashMap<>();
            for (PropertyDescriptor property : propertyDescriptors) {
                if ("class".equals(property.getName())) {
                    continue;
                }
                parm.put(property.getName(), property.getReadMethod().invoke(object).toString());
            }
            return parm;
        } catch (IntrospectionException e) {
            throw new ApiException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new ApiException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new ApiException(e.getMessage());
        }
    }

    /**
     * 对象转map-string
     *
     * @param
     * @return
     * @author frank(付帅)
     * @date 2021/4/13
     **/
    public static Map<String, Object> toMapObject(Object object) {
        if (null == object) {
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Map<String, Object> parm = new HashMap<>();
            for (PropertyDescriptor property : propertyDescriptors) {
                if ("class".equals(property.getName())) {
                    continue;
                }
                parm.put(property.getName(), property.getReadMethod().invoke(object));
            }
            return parm;
        } catch (IntrospectionException e) {
            throw new ApiException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new ApiException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new ApiException(e.getMessage());
        }
    }
}
