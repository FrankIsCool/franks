package com.franks.util.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.*;

/**
 * 时间工具类
 *
 * @author frank
 * @module
 * @date 2021/9/4 14:50
 */
public class InstantDateUtil {

    /**
     * Instant转成Date
     *
     * @param date 时间戳
     * @return
     */
    public static Date toDate(Instant date) {
        return null == date ? null : Date.from(date);
    }


    /**
     * Instant转成Long
     *
     * @param date 时间
     * @return
     */
    public static long toLong(Instant date) {
        return null == date ? null : toDate(date).getTime();
    }

    /**
     * Instant转成Timestamp
     *
     * @param date 时间
     * @return
     */
    public static Timestamp toTimestamp(Instant date) {
        return null == date ? null : new Timestamp(toDate(date).getTime());
    }

    /**
     * Instant转成String
     *
     * @param date 时间
     * @return
     */
    public static String toStr(Instant date) {
        return null == date ? null : toStr(date, DateProperties.DATE_ALL);
    }

    /**
     * Instant转成String
     *
     * @param date   时间
     * @param format 时间格式
     * @return
     */
    public static String toStr(Instant date, String format) {
        return null == date ? null : new SimpleDateFormat(format).format(toDate(date));
    }
    /**
     * 获取当前系统时间(原始格式)-Instant
     *
     * @return
     */
    public static Instant getDate() {
        return Instant.now();
    }
    /**
     * 某个时间戳增加时间
     *
     * @param date  日期
     * @param times 增加时间
     * @return 返回增加后的日期
     */
    public static Instant addTime(Instant date, int... times) {
        if (null == times) {
            return date;
        }
        if (times.length > 7) {
            throw new RuntimeException("times length max 7");
        }
        return date.plus(Duration.of(times[0], YEARS))
                .plus(Duration.of(times[1], MONTHS))
                .plus(Duration.of(times[2], DAYS))
                .plus(Duration.of(times[3], HOURS))
                .plus(Duration.of(times[4], MINUTES))
                .plus(Duration.of(times[5], SECONDS))
                .plus(Duration.of(times[6], MILLIS));
    }

    /**
     * 某个日期增加年
     *
     * @param date  日期
     * @param years 增加年数值
     * @return 返回增加后的日期
     */
    public static Instant addYear(Instant date, int years) {
        return addTime(date, years);
    }

    /**
     * 某个日期增加月
     *
     * @param date   日期
     * @param months 增加月数值
     * @return 返回增加后的日期
     */
    public static Instant addMonth(Instant date, int months) {
        return addTime(date, 0, months);
    }

    /**
     * 某个日期增加日
     *
     * @param date 日期
     * @param days 增加天数值
     * @return 返回增加后的日期
     */
    public static Instant addDay(Instant date, int days) {
        return addTime(date, 0, 0, days);
    }

    /**
     * 某个日期增加小时
     *
     * @param date  日期
     * @param hours 增加小时数值
     * @return 返回增加后的日期
     */
    public static Instant addHour(Instant date, int hours) {
        return addTime(date, 0, 0, 0, hours);
    }

    /**
     * 某个日期增加分钟
     *
     * @param date    日期
     * @param minutes 增加分钟数值
     * @return 返回增加后的日期
     */
    public static Instant addMinute(Instant date, int minutes) {
        return addTime(date, 0, 0, 0, 0, minutes);
    }

    /**
     * 某个日期增加秒
     *
     * @param date    日期
     * @param seconds 增加秒数值
     * @return 返回增加后的日期
     */
    public static Instant addSecond(Instant date, int seconds) {
        return addTime(date, 0, 0, 0, 0, 0, seconds);
    }

    /**
     * 某个日期增加毫秒
     *
     * @param date        日期
     * @param milliSecond 增加毫秒数值
     * @return 返回增加后的日期
     */
    public static Instant addMilliSecond(Instant date, int milliSecond) {
        return addTime(date, 0, 0, 0, 0, 0, 0, milliSecond);
    }

    /**
     * 获取日期中的某个数值
     *
     * @param date     日期
     * @param dateType 1：年，2：月，7：周，:5：日，11：时，12：分，13：秒，14：毫秒
     * @return
     */
    private static Integer getTime(Instant date, int dateType) {
        return DateUtil.getTime(toDate(date), dateType);
    }

    /**
     * 获取日期:年
     *
     * @param date 日期
     * @return 年份
     */
    public static Integer getYear(Instant date) {
        return getTime(date, Calendar.YEAR);
    }

    /**
     * 获取日期:月
     *
     * @param date 日期
     * @return 月份
     */
    public static Integer getMonth(Instant date) {
        return getTime(date, Calendar.MONTH);
    }

    /**
     * 获取日期:日
     *
     * @param date 日期
     * @return 日
     */
    public static Integer getDay(Instant date) {
        return getTime(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期:星期几
     *
     * @param date
     * @return
     */
    public static Integer getWeek(Instant date) {
        return getTime(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日期:时
     *
     * @param date 日期
     * @return 小时
     */
    public static Integer getHour(Instant date) {
        return getTime(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期:分
     *
     * @param date 日期
     * @return 分钟
     */
    public static Integer getMinute(Instant date) {
        return getTime(date, Calendar.MINUTE);
    }

    /**
     * 获取日期:秒
     *
     * @param date 日期
     * @return 秒
     */
    public static Integer getSecond(Instant date) {
        return getTime(date, Calendar.SECOND);
    }

    /**
     * 获取日期:毫秒
     *
     * @param date 日期
     * @return 毫秒
     */
    public static Integer getMillisecond(Instant date) {
        return getTime(date, Calendar.MILLISECOND);
    }

    /**
     * 相差时间-毫秒
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differMillisecond(Instant startTime, Instant endTime) {
        return ChronoUnit.MILLIS.between(startTime, endTime);
    }

    /**
     * 相差时间-秒-向下取整
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differSecond(Instant startTime, Instant endTime) {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    /**
     * 相差时间-分钟-向下取整
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differMinute(Instant startTime, Instant endTime) {
        return ChronoUnit.MINUTES.between(startTime, endTime);
    }

    /**
     * 相差时间-小时-向下取整
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differHour(Instant startTime, Instant endTime) {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    /**
     * 相差时间-天-向下取整
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differDay(Instant startTime, Instant endTime) {
        return DAYS.between(startTime, endTime);
    }

    /**
     * 相差时间-周-向下取整
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differWeek(Instant startTime, Instant endTime) {
        return ChronoUnit.WEEKS.between(startTime, endTime);
    }

    /**
     * 相差时间-月-向下取整
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differMonth(Instant startTime, Instant endTime) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(toDate(startTime));
        end.setTime(toDate(endTime));
        return Long.valueOf((end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12 + (end.get(Calendar.MONTH) - start.get(Calendar.MONTH)));
    }

    /**
     * 相差时间-月-向下取整
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static Long differYear(Instant startTime, Instant endTime) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(toDate(startTime));
        end.setTime(toDate(endTime));
        return Long.valueOf(end.get(Calendar.YEAR) - start.get(Calendar.YEAR));
    }
}
