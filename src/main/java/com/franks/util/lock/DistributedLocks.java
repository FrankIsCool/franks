package com.franks.util.lock;

import com.franks.util.enums.ExpireTimeEnums;

import java.lang.annotation.*;

/**
 * 类：分布式锁
 * 内容：
 * 创建人：frank-fu
 * 时间：2020/11/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DistributedLocks {
    /**
     * 缓存key
     *
     * @return
     */
    public String key() default "";

    /**
     * 缓存时效,默认无限期
     *
     * @return
     */
    public ExpireTimeEnums expire() default ExpireTimeEnums.NONE;
}
