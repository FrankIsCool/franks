package com.franks.util.lock;

import com.franks.util.empty.EmptyUtil;
import com.franks.util.enums.ExpireTimeEnums;
import com.franks.util.exception.ApiException;
import com.franks.util.redis.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类：分布式锁拦截
 * 内容：
 * 创建人：frank-fu
 * 时间：2020/11/20
 */
@Component
@Aspect
public class DistributedLocksAspect {

    private final Logger log = LoggerFactory.getLogger(DistributedLocksAspect.class);

    @Autowired
    RedisUtils redisUtils;

    @Around("@annotation(annotation)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLocks annotation) throws Throwable {
        String key = annotation.key();
        if (EmptyUtil.isNotEmpty(redisUtils.get(key))) {
            throw new ApiException("当前有正在的执行任务，请稍后再试");
        }
        redisUtils.set(key, 1, ExpireTimeEnums.FIVE_MIN.getTime());
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } finally {
            redisUtils.del(key);
        }
        return proceed;
    }
}
