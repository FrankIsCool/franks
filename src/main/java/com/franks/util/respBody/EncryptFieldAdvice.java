package com.franks.util.respBody;

import com.franks.util.exception.ApiException;
import com.franks.util.mysql.DecryptField;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 加密返回参数
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
@Component
public class EncryptFieldAdvice {
    /**
     * 对含注解字段加密
     *
     * @param t
     */
    public static <T> void encryptField(T t, IEncryptField iEncryptField) {
        Field[] declaredFields = getAllFields(t);
        try {
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, iEncryptField.encrypt(fieldValue));
                        }
                    }
                    if (field.getType().getName() == Object.class.getName()) {
                        if (field.get(t) instanceof Collection) {
                            ((List) field.get(t)).stream().forEach(obj -> encryptField(obj, iEncryptField));
                        } else {
                            encryptField(field.get(t), iEncryptField);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new ApiException(e);
        }
    }

    /**
     * 获取所有类和父类属性
     */
    private static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 对含注解字段解密
     *
     * @param t d
     */
    public static <T> void decryptField(T t, IEncryptField iEncryptField) {
        Field[] declaredFields = getAllFields(t);
        try {
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(DecryptField.class) && field.getType().toString().endsWith("String")) {
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, iEncryptField.encrypt(fieldValue));
                        }
                    }
                    if (field.getType().getName() == Object.class.getName()) {
                        if (field.get(t) instanceof Collection) {
                            ((List) field.get(t)).stream().forEach(obj -> encryptField(obj, iEncryptField));
                        } else {
                            encryptField(field.get(t), iEncryptField);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new ApiException(e);
        }
    }
}