package com.franks.util.mysql;


import com.franks.util.respBody.EncryptField;
import com.franks.util.sign.SignUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAutoConfiguration
public class DBRSA{

    @Value("${spring.mysql.privateKey}")
    static String privateKey;
    @Value("${spring.redis.password}")
    String password;
    /**
     * 对含注解字段加密
     * @param  t
     */
    public static <T> void encryptField(T t) {
        Field[] declaredFields = getAllFields(t);
        try {
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String)field.get(t);
                        if(StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, SignUtils.RSA.createSign(fieldValue,privateKey,"uft-8"));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有类和父类属性
     */
    private static Field[] getAllFields(Object object){
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 对含注解字段解密
     * @param  t  d
     */
    public static <T> void decryptField(T t) {
        Field[] declaredFields = getAllFields(t);
        try {
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(DecryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String)field.get(t);
                        if(StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, SignUtils.RSA.decrypt(fieldValue,privateKey,"uft-8"));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
