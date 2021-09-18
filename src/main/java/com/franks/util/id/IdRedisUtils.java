package com.franks.util.id;

import com.franks.util.context.SpringContextUtil;
import com.franks.util.redis.RedisUtils;
import org.springframework.stereotype.Service;

/**
 * redis生成唯一id
 *
 * @author frank
 * @date 2021/9/18 17:16
 */
@Service
public class IdRedisUtils {

    private static final String KEY_PREFFIX = "generate_id_";

    /**
     * 生成唯一id
     *
     * @param sequence  id类型（支持多表并行生成id）
     * @param preffix   前缀
     * @param numLength 长度
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/18 17:17
     */
    public static String getId(String sequence, String preffix, Integer numLength) {
        RedisUtils redisUtils = SpringContextUtil.getBean(RedisUtils.class);
        String key = KEY_PREFFIX + sequence;
        Long id = redisUtils.incr(key, 1);
        return preffix + String.format("%1$0" + numLength + "d", id);
    }
}
