package com.franks.util.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author frank
 * @module
 * @date 2021/9/4 14:50
 */
public class TimestampDateUtil {

    /**
     * Timestamp转成Date
     *
     * @param date 时间戳
     * @return
     */
    public static Date toDate(Timestamp date) {
        return null == date ? null : LongDateUtil.toDate(date.getTime());
    }


    /**
     * Timestamp转成Long
     *
     * @param date 时间
     * @return
     */
    public static long toLong(Timestamp date) {
        return null == date ? null : date.getTime();
    }

    /**
     * Timestamp转成Timestamp
     *
     * @param date 时间
     * @return
     */
    public static Instant toInstant(Timestamp date) {
        return null == date ? null : date.toInstant();
    }

    /**
     * Timestamp转成String
     *
     * @param date 时间
     * @return
     */
    public static String toStr(Timestamp date) {
        return null == date ? null :DateUtil.dateToStr(date);
    }

    /**
     * Timestamp转成String
     *
     * @param date   时间
     * @param format 时间格式
     * @return
     */
    public static String toStr(Timestamp date, String format) {
        return null == date ? null : new SimpleDateFormat(format).format(DateUtil.dateToStr(date));
    }
    /**
     * 获取当前系统时间(原始格式)-Instant
     *
     * @return
     */
    public static Timestamp getDate() {
        return new Timestamp(LongDateUtil.getDate());
    }
    /**
     * 某个时间戳增加时间
     *
     * @param date  日期
     * @param times 增加时间
     * @return 返回增加后的日期
     */
    public static Timestamp addTime(Timestamp date, int... times) {
        return DateUtil.dateToTimestamp(DateUtil.addTime(toDate(date), times[0], times[1], times[2], times[3], times[4], times[5], times[6]));
    }

    /**
     * 某个日期增加年
     *
     * @param date  日期
     * @param years 增加年数值
     * @return 返回增加后的日期
     */
    public static Timestamp addYear(Timestamp date, int years) {
        return addTime(date, years);
    }

    /**
     * 某个日期增加月
     *
     * @param date   日期
     * @param months 增加月数值
     * @return 返回增加后的日期
     */
    public static Timestamp addMonth(Timestamp date, int months) {
        return addTime(date, 0, months);
    }

    /**
     * 某个日期增加日
     *
     * @param date 日期
     * @param days 增加天数值
     * @return 返回增加后的日期
     */
    public static Timestamp addDay(Timestamp date, int days) {
        return addTime(date, 0, 0, days);
    }

    /**
     * 某个日期增加小时
     *
     * @param date  日期
     * @param hours 增加小时数值
     * @return 返回增加后的日期
     */
    public static Timestamp addHour(Timestamp date, int hours) {
        return addTime(date, 0, 0, 0, hours);
    }

    /**
     * 某个日期增加分钟
     *
     * @param date    日期
     * @param minutes 增加分钟数值
     * @return 返回增加后的日期
     */
    public static Timestamp addMinute(Timestamp date, int minutes) {
        return addTime(date, 0, 0, 0, 0, minutes);
    }

    /**
     * 某个日期增加秒
     *
     * @param date    日期
     * @param seconds 增加秒数值
     * @return 返回增加后的日期
     */
    public static Timestamp addSecond(Timestamp date, int seconds) {
        return addTime(date, 0, 0, 0, 0, 0, seconds);
    }

    /**
     * 某个日期增加毫秒
     *
     * @param date        日期
     * @param milliSecond 增加毫秒数值
     * @return 返回增加后的日期
     */
    public static Timestamp addMilliSecond(Timestamp date, int milliSecond) {
        return addTime(date, 0, 0, 0, 0, 0, 0, milliSecond);
    }
    /**
     * 获取日期中的某个数值
     *
     * @param date     日期
     * @param dateType 1：年，2：月，7：周，:5：日，11：时，12：分，13：秒，14：毫秒
     * @return
     */
    private static Integer getTime(Timestamp date, int dateType) {
        return DateUtil.getTime(toDate(date),dateType);
    }
    /**
     * 获取日期:年
     *
     * @param date 日期
     * @return 年份
     */
    public static Integer getYear(Timestamp date) {
        return getTime(date, Calendar.YEAR);
    }

    /**
     * 获取日期:月
     *
     * @param date 日期
     * @return 月份
     */
    public static Integer getMonth(Timestamp date) {
        return getTime(date, Calendar.MONTH);
    }

    /**
     * 获取日期:日
     *
     * @param date 日期
     * @return 日
     */
    public static Integer getDay(Timestamp date) {
        return getTime(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期:星期几
     *
     * @param date
     * @return
     */
    public static Integer getWeek(Timestamp date) {
        return getTime(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日期:时
     *
     * @param date 日期
     * @return 小时
     */
    public static Integer getHour(Timestamp date) {
        return getTime(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期:分
     *
     * @param date 日期
     * @return 分钟
     */
    public static Integer getMinute(Timestamp date) {
        return getTime(date, Calendar.MINUTE);
    }

    /**
     * 获取日期:秒
     *
     * @param date 日期
     * @return 秒
     */
    public static Integer getSecond(Timestamp date) {
        return getTime(date, Calendar.SECOND);
    }

    /**
     * 获取日期:毫秒
     *
     * @param date 日期
     * @return 毫秒
     */
    public static Integer getMillisecond(Timestamp date) {
        return getTime(date, Calendar.MILLISECOND);
    }
}
