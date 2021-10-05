package com.franks.util.param;

import com.franks.util.empty.EmptyUtil;
import com.franks.util.param.decrypt.IDecryptField;
import com.franks.util.param.encrypt.IEncryptField;
import com.franks.util.param.valid.IValidField;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 参数加密，解密
 *
 * @author frank
 * @module
 * @date 2021/9/19 15:40
 */
@Component
public class ParamAdvice {
    /**
     * 获取所有类和父类属性
     *
     * @param object 对象
     * @return
     * @Author frank
     * @Date 2021/9/24 15:04
     */
    private static List<Field> getAllFields(Object object) {
        if(object instanceof Number || object instanceof Map|| object instanceof Date){
            return null;
        }
        try {
            Class clazz = object.getClass();
            List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
            while ((clazz = clazz.getSuperclass()) != null) {
                fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            }
            return fieldList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

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
        getAllFields(t).stream().forEach(field -> {
            field.setAccessible(true);
            try {
                Object o = field.get(t);
                if (EmptyUtil.isEmpty(o) || o instanceof Number || o instanceof Map|| o instanceof Date) {
                    return;
                } else if (o instanceof Collection) {
                    ((List) o).stream().forEach(obj -> encryptField(obj, annotationClass, encryptField));
                } else if (o instanceof CharSequence) {
                    if (EmptyUtil.isNotEmpty(o) && field.isAnnotationPresent(annotationClass)) {
                        field.set(t, encryptField.encrypt((String) o));
                    }
                } else {
                    encryptField(o, annotationClass, encryptField);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
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
        getAllFields(t).stream().forEach(field -> {
            field.setAccessible(true);
            try {
                Object o = field.get(t);
                if (o instanceof Collection) {
                    ((List) o).stream().forEach(obj -> decryptField(obj, annotationClass, decryptField));
                } else if (field.getType().getName() == Object.class.getName()) {
                    decryptField(o, annotationClass, decryptField);
                } else if (field.isAnnotationPresent(annotationClass) && o instanceof String) {
                    if (EmptyUtil.isNotEmpty(o)) {
                        field.set(t, decryptField.decrypt((String) o));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 参数校验
     *
     * @param t               待校验对象
     * @param annotationClass 注解类型
     * @param validField      校验
     * @return void
     * @Author frank
     * @Date 2021/9/24 15:05
     */
    public static <T> void validField(T t, Class<? extends Annotation> annotationClass, IValidField validField) {
        getAllFields(t).stream().forEach(field -> {
            field.setAccessible(true);
            try {
                Object o = field.get(t);
                if (o instanceof Collection) {
                    ((List) o).stream().forEach(obj -> validField(obj, annotationClass, validField));
                } else if (field.getType().getName() == Object.class.getName()) {
                    validField(o, annotationClass, validField);
                } else if (field.isAnnotationPresent(annotationClass) && o instanceof String) {
                    if (EmptyUtil.isNotEmpty(o)) {
                        validField.vaild((String) o);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}