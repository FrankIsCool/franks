package com.franks.util.id;


import com.franks.util.date.DateProperties;
import com.franks.util.date.StrDateUtil;

/**
 * 生成id
 * 每毫秒可产生4096不同id
 * 最多可以使用69.73年
 *
 * @author chendehua
 */
public class IdGen {
    //机器节点
    private long workerId;
    //数据中心
    private long datacenterId;
    //序列号（自增）
    private long sequence = 0L;
    //初始时间（Thu, 04 Nov 2010 01:42:54 GMT）
    //主要是用来计算时间差（当前时间-twepoch）
    //这个时间最好是系统第一次上线时间,这样可以使用大概69.73年
    private long twepoch = 1628850054648L;
    //机器节点ID长度
    private long workerIdBits = 5L;
    //数据中心ID长度
    private long datacenterIdBits = 5L;
    //最大支持机器节点数0~31，一共32个
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    //最大支持数据中心节点数0~31，一共32个
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //序列号长度
    private long sequenceBits = 12L;
    //机器节点需要左移位数（sequenceBits = 12）
    private long workerIdShift = sequenceBits;
    //数据中心节点需要左移位数（sequenceBits + workerIdBits = 17）
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //时间毫秒数需要左移位数（sequenceBits + workerIdBits + datacenterIdBits = 22）
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    //sequence的最大值4095，如果为4095，则sequence=0
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    //上次获取id的时间，默认为-1
    private long lastTimestamp = -1L;

    private static class IdGenHolder {
        private static final IdGen instance = new IdGen();
    }

    public static IdGen getInStance() {
        return IdGenHolder.instance;
    }

    public IdGen() {
        this(0L, 0L);
    }

    public IdGen(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    //每毫秒4096个id，可使用69年
    public synchronized long getId() {
        //获取当前毫秒数
        long timestamp = timeGen();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp); //自旋等待到下一毫秒
            }
        } else {
            //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
            sequence = 0L;
        }
        //保存本次生成id的时间
        lastTimestamp = timestamp;
        //核心计算
        //1,(timestamp - twepoch) << timestampLeftShift （当前时间减去初始时间）的值左移22位
        //2,datacenterId << datacenterIdShift 数据中心左移17位
        //3,workerId << workerIdShift 机器节点左移12位
        //利用|的特性，把数据拼接起来
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    //每毫秒4096个id，使用年份不受限制
    public synchronized String getSn() {
        //获取当前毫秒数
        long timestamp = timeGen();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp); //自旋等待到下一毫秒
            }
        } else {
            //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
            sequence = 0L;
        }
        //保存本次生成id的时间
        lastTimestamp = timestamp;
        //核心计算
        //1,timestamp 转成日期格式‘yyyyMMddHHMMssSSS’
        //2,datacenterId << datacenterIdShift 数据中心左移17位
        //3,workerId << workerIdShift 机器节点左移12位
        //利用|的特性，把数据拼接起来
        return StrDateUtil.getDate(DateProperties.DATE_2_ALL_INFO) + ((datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence);
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}