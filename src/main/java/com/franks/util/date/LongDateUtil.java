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
public class LongDateUtil {

    /**
     * Date转long
     *
     * @param date 时间
     * @return
     */
    public static long toLong(Date date) {
        return null == date ? null : date.getTime();
    }
    /**
     * Instant转成Long
     *
     * @param date 时间
     * @return
     */
    public static long toLong(Instant date) {
        return null == date ? null : InstantDateUtil.toDate(date).getTime();
    }
    /**
     * string转成Long
     *
     * @param date 时间
     * @param format 时间格式
     * @return
     */
    public static long toLong(String date, String format) {
        return StrDateUtil.toDate(date,format).getTime();
    }
    /**
     * string转成Long
     *
     * @param date 时间
     * @return
     */
    public static long toLong(String date) {
        return StrDateUtil.toDate(date).getTime();
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
     * long转Date
     *
     * @param date 时间戳
     * @return
     */
    public static Date toDate(long date) {
        return new Date(date);
    }

    /**
     * long转成Instant
     *
     * @param date 时间
     * @return
     */
    public static Instant toInstant(long date) {
        return toDate(date).toInstant();
    }
    /**
     * long转成Timestamp
     *
     * @param date 时间
     * @return
     */
    public static Timestamp toTimestamp(long date) {
        return new Timestamp(date);
    }
    /**
     * long转成String
     *
     * @param date 时间
     * @return
     */
    public static String toStr(long date) {
        return toStr(date, DateProperties.DATE_ALL);
    }

    /**
     * long转成String
     *
     * @param date   时间
     * @param format 时间格式
     * @return
     */
    public static String toStr(long date, String format) {
        return new SimpleDateFormat(format).format(toDate(date));
    }
    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static long getDate() {
        return System.currentTimeMillis();
    }
    /**
     * 某个时间戳增加时间
     *
     * @param date  日期
     * @param times 增加时间
     * @return 返回增加后的日期
     */
    public static Long addTime(long date, int... times) {
        return DateUtil.addTime(toDate(date), times[0], times[1], times[2], times[3], times[4], times[5], times[6]).getTime();
    }
    /**
     * 某个日期增加年
     *
     * @param date  日期
     * @param years 增加年数值
     * @return 返回增加后的日期
     */
    public static Long addYear(long date, int years) {
        return addTime(date, years);
    }

    /**
     * 某个日期增加月
     *
     * @param date   日期
     * @param months 增加月数值
     * @return 返回增加后的日期
     */
    public static Long addMonth(long date, int months) {
        return addTime(date, 0, months);
    }

    /**
     * 某个日期增加日
     *
     * @param date 日期
     * @param days 增加天数值
     * @return 返回增加后的日期
     */
    public static Long addDay(long date, int days) {
        return addTime(date, 0, 0, days);
    }

    /**
     * 某个日期增加小时
     *
     * @param date  日期
     * @param hours 增加小时数值
     * @return 返回增加后的日期
     */
    public static Long addHour(long date, int hours) {
        return addTime(date, 0, 0, 0, hours);
    }

    /**
     * 某个日期增加分钟
     *
     * @param date    日期
     * @param minutes 增加分钟数值
     * @return 返回增加后的日期
     */
    public static Long addMinute(long date, int minutes) {
        return addTime(date, 0, 0, 0, 0, minutes);
    }

    /**
     * 某个日期增加秒
     *
     * @param date    日期
     * @param seconds 增加秒数值
     * @return 返回增加后的日期
     */
    public static Long addSecond(long date, int seconds) {
        return addTime(date, 0, 0, 0, 0, 0, seconds);
    }

    /**
     * 某个日期增加毫秒
     *
     * @param date        日期
     * @param milliSecond 增加毫秒数值
     * @return 返回增加后的日期
     */
    public static Long addMilliSecond(long date, int milliSecond) {
        return addTime(date, 0, 0, 0, 0, 0, 0, milliSecond);
    }
    /**
     * 获取日期中的某个数值
     *
     * @param date     日期
     * @param dateType 1：年，2：月，7：周，:5：日，11：时，12：分，13：秒，14：毫秒
     * @return
     */
    private static Integer getTime(long date, int dateType) {
        return DateUtil.getTime(toDate(date),dateType);
    }
    /**
     * 获取日期:年
     *
     * @param date 日期
     * @return 年份
     */
    public static Integer getYear(long date) {
        return getTime(date, Calendar.YEAR);
    }

    /**
     * 获取日期:月
     *
     * @param date 日期
     * @return 月份
     */
    public static Integer getMonth(long date) {
        return getTime(date, Calendar.MONTH);
    }

    /**
     * 获取日期:日
     *
     * @param date 日期
     * @return 日
     */
    public static Integer getDay(long date) {
        return getTime(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期:星期几
     *
     * @param date
     * @return
     */
    public static Integer getWeek(long date) {
        return getTime(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日期:时
     *
     * @param date 日期
     * @return 小时
     */
    public static Integer getHour(long date) {
        return getTime(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期:分
     *
     * @param date 日期
     * @return 分钟
     */
    public static Integer getMinute(long date) {
        return getTime(date, Calendar.MINUTE);
    }

    /**
     * 获取日期:秒
     *
     * @param date 日期
     * @return 秒
     */
    public static Integer getSecond(long date) {
        return getTime(date, Calendar.SECOND);
    }

    /**
     * 获取日期:毫秒
     *
     * @param date 日期
     * @return 毫秒
     */
    public static Integer getMillisecond(long date) {
        return getTime(date, Calendar.MILLISECOND);
    }
    /**
     * 相差时间-毫秒
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differMillisecond(long startTime,long endTime) {
        return startTime-endTime;
    }
    /**
     * 相差时间-秒-向下取整
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differSecond(long startTime,long endTime) {
        return differMillisecond(startTime,endTime)/1000;
    }
    /**
     * 相差时间-分钟-向下取整
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differMinute(long startTime,long endTime) {
        return differSecond(startTime,endTime)/60;
    }
    /**
     * 相差时间-小时-向下取整
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differHour(long startTime,long endTime) {
        return differMinute(startTime,endTime)/60;
    }
    /**
     * 相差时间-天-向下取整
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differDay(long startTime,long endTime) {
        return differHour(startTime,endTime)/24;
    }
    /**
     * 相差时间-周-向下取整
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differWeek(long startTime,long endTime) {
        return differDay(startTime,endTime)/7;
    }
    /**
     * 相差时间-月-向下取整
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differMonth(long startTime,long endTime) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(toDate(startTime));
        end.setTime(toDate(endTime));
        return Long.valueOf((end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12 + (end.get(Calendar.MONTH) - start.get(Calendar.MONTH)));
    }
    /**
     * 相差时间-月-向下取整
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differYear(long startTime,long endTime) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(toDate(startTime));
        end.setTime(toDate(endTime));
        return Long.valueOf(end.get(Calendar.YEAR) - start.get(Calendar.YEAR));
    }
}
