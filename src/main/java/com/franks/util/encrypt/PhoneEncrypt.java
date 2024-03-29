package com.franks.util.encrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 手机号加密
 *
 * @author frank
 * @date 2021/9/18 18:10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneEncrypt {

}
