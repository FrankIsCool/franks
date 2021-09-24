package com.franks.util.encrypt;

import com.franks.util.exception.ApiException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 参数加密，解密
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
@Component
public class EncryptParamUtil {
    /**
     * 参数加密
     *
     * @param t               待加密对象
     * @param annotationClass 注解类型
     * @param encryptField    加密接口
     * @return void
     * @Author frank
     * @Date 2021/9/24 15:05
     */
    public static <T> void encryptField(T t, Class<? extends Annotation> annotationClass, IEncryptField encryptField) {
        Field[] declaredFields = getAllFields(t);
        try {
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (field.get(t) instanceof Collection) {
                        ((List) field.get(t)).stream().forEach(obj -> encryptField(obj, annotationClass, encryptField));
                    }else if (field.getType().getName() == Object.class.getName()) {
                        encryptField(field.get(t), annotationClass, encryptField);
                    }else if (field.isAnnotationPresent(annotationClass) && field.get(t) instanceof String) {
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, encryptField.encrypt(fieldValue));
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
     *
     * @param object 对象
     * @return
     * @Author frank
     * @Date 2021/9/24 15:04
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
     * 参数解密
     *
     * @param t               待解密对象
     * @param annotationClass 注解类型
     * @param decryptField    解密方法
     * @return void
     * @Author frank
     * @Date 2021/9/24 15:04
     */
    public static <T> void decryptField(T t, Class<? extends Annotation> annotationClass, IDecryptField decryptField) {
        Field[] declaredFields = getAllFields(t);
        try {
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(annotationClass)) {
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, decryptField.decrypt(fieldValue));
                        }
                    }
                    if (field.getType().getName() == Object.class.getName()) {
                        if (field.get(t) instanceof Collection) {
                            ((List) field.get(t)).stream().forEach(obj -> decryptField(obj, annotationClass, decryptField));
                        } else {
                            decryptField(field.get(t), annotationClass, decryptField);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new ApiException(e);
        }
    }
}