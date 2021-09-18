package com.franks.util.redisson;

/**
 * 队列事件监听接口，需要实现这个方法
 *
 * @module
 * @author frank
 * @date 2021/8/19 10:50
 */
public interface RedisDelayedQueueListener<T> {
    /**
     * 执行方法
     *
     * @param t
     */
    void invoke(T t);
}
