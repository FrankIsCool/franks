package com.franks.util.id;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 唯一id工具类
 *
 * @author frank
 * @date 2021/9/18 10:20
 */
public class SequenceUtil {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static final SequenceUtil instance = new SequenceUtil();

    private SequenceUtil() {
    }

    public static SequenceUtil getInstance() {
        return instance;
    }

    public Long createId() {
        try {
            lock.readLock().lock();
            final IdGen gen = IdGen.getInStance();
            return gen.getId();
        } finally {
            lock.readLock().unlock();
        }
    }

    public String createIdStr() {
        return createId() + "";
    }

    public String createSn() {
        try {
            lock.readLock().lock();
            final IdGen gen = IdGen.getInStance();
            return gen.getSn();
        } finally {
            lock.readLock().unlock();
        }
    }
}
