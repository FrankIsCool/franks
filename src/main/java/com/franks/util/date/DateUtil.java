package com.franks.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 *
 * @author frank
 * @module
 * @date 2021/9/4 14:50
 */
public class DateUtil {
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
     * long转Date
     *
     * @param date 时间戳
     * @return
     */
    public static Date toDate(long date) {
        return new Date(date);
    }
    /**
     * String转Date
     *
     * @param data   日期字符串
     * @param format 时间格式
     * @return
     */
    public static Date toDate(String data, String format) {
        try {
            return new SimpleDateFormat(format).parse(data);
        } catch (ParseException e) {
            throw new RuntimeException("date string to date fail");
        }
    }
    /**
     * String转Date
     *
     * @param data 日期字符串
     * @return
     */
    public static Date toDate(String data) {
        return toDate(data,DateProperties.DATE_ALL);
    }
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
     * Date转long
     *
     * @param date 时间
     * @return
     */
    public static long toLong(Date date) {
        return null == date ? null : date.getTime();
    }

    /**
     * Date转成Instant
     *
     * @param date 时间
     * @return
     */
    public static Instant toInstant(Date date) {
        return null == date ? null : date.toInstant();
    }
    /**
     * Date转Timestamp
     *
     * @param date 时间
     * @return
     */
    public static Timestamp toTimestamp(Date date) {
        return null == date ? null : new Timestamp(date.getTime());
    }
    /**
     * Date转成String
     *
     * @param date 时间
     * @return
     */
    public static String toStr(Date date) {
        return null == date ? null : toStr(date, DateProperties.DATE_ALL);
    }

    /**
     * Date转成String
     *
     * @param date 时间
     * @return
     */
    public static String toStr(Date date, String format) {
        return null == date ? null : new SimpleDateFormat(format).format(date);
    }
    /**
     * 获取当前系统时间(原始格式)
     */
    public static Date getDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 某个日期增加时间
     *
     * @param date  日期
     * @param times 增加时间
     * @return 返回增加后的日期
     */
    public static Date addTime(Date date, int... times) {
        if (null == times) {
            return date;
        }
        if (times.length > 7) {
            throw new RuntimeException("times length max 7");
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.YEAR, times[0]);
        cal.add(Calendar.MONTH, times[1]);
        cal.add(Calendar.DATE, times[2]);
        cal.add(Calendar.HOUR, times[3]);
        cal.add(Calendar.MINUTE, times[4]);
        cal.add(Calendar.SECOND, times[5]);
        cal.add(Calendar.MILLISECOND, times[6]);
        return cal.getTime();
    }


    /**
     * 当前时间增加时间
     *
     * @param times 增加时间
     * @return 返回增加后的日期
     */
    public static Date addTime(int... times) {
        return addTime(getDate(), times);
    }

    /**
     * 当前时间增加年
     *
     * @param years 增加年数值
     * @return 返回增加后的日期
     */
    public static Date addYear(int years) {
        return addTime(getDate(), years, 0, 0, 0, 0, 0, 0);
    }

    /**
     * 当前时间增加月
     *
     * @param months 增加月数值
     * @return 返回增加后的日期
     */
    public static Date addMonth(int months) {
        return addTime(getDate(), 0, months, 0, 0, 0, 0, 0);
    }

    /**
     * 当前时间增加日
     *
     * @param days 增加天数值
     * @return 返回增加后的日期
     */
    public static Date addDay(int days) {
        return addTime(getDate(), 0, 0, days, 0, 0, 0, 0);
    }

    /**
     * 当前时间增加小时
     *
     * @param hours 增加小时数值
     * @return 返回增加后的日期
     */
    public static Date addHour(int hours) {
        return addTime(getDate(), 0, 0, 0, hours, 0, 0, 0);
    }

    /**
     * 当前时间增加分钟
     *
     * @param minutes 增加分钟数值
     * @return 返回增加后的日期
     */
    public static Date addMinute(int minutes) {
        return addTime(getDate(), 0, 0, 0, 0, minutes, 0, 0);
    }

    /**
     * 当前时间增加秒
     *
     * @param seconds 增加秒数值
     * @return 返回增加后的日期
     */
    public static Date addSecond(int seconds) {
        return addTime(getDate(), 0, 0, 0, 0, 0, seconds, 0);
    }

    /**
     * 当前时间增加毫秒
     *
     * @param milliSecond 增加毫秒数值
     * @return 返回增加后的日期
     */
    public static Date addMilliSecond(int milliSecond) {
        return addTime(getDate(), 0, 0, 0, 0, 0, 0, milliSecond);
    }


    /**
     * 某个日期增加年
     *
     * @param date  日期
     * @param years 增加年数值
     * @return 返回增加后的日期
     */
    public static Date addYear(Date date, int years) {
        return addTime(date, years, 0, 0, 0, 0, 0);
    }

    /**
     * 某个日期增加月
     *
     * @param date   日期
     * @param months 增加月数值
     * @return 返回增加后的日期
     */
    public static Date addMonth(Date date, int months) {
        return addTime(date, 0, months, 0, 0, 0, 0);
    }

    /**
     * 某个日期增加日
     *
     * @param date 日期
     * @param days 增加天数值
     * @return 返回增加后的日期
     */
    public static Date addDay(Date date, int days) {
        return addTime(date, 0, 0, days, 0, 0, 0);
    }

    /**
     * 某个日期增加小时
     *
     * @param date  日期
     * @param hours 增加小时数值
     * @return 返回增加后的日期
     */
    public static Date addHour(Date date, int hours) {
        return addTime(date, 0, 0, 0, hours, 0, 0);
    }

    /**
     * 某个日期增加分钟
     *
     * @param date    日期
     * @param minutes 增加分钟数值
     * @return 返回增加后的日期
     */
    public static Date addMinute(Date date, int minutes) {
        return addTime(date, 0, 0, 0, 0, minutes, 0);
    }

    /**
     * 某个日期增加秒
     *
     * @param date    日期
     * @param seconds 增加秒数值
     * @return 返回增加后的日期
     */
    public static Date addSecond(Date date, int seconds) {
        return addTime(date, 0, 0, 0, 0, 0, seconds);
    }

    /**
     * 某个日期增加毫秒
     *
     * @param date        日期
     * @param milliSecond 增加毫秒数值
     * @return 返回增加后的日期
     */
    public static Date addMilliSecond(Date date, int milliSecond) {
        return addTime(date, 0, 0, 0, 0, 0, 0, milliSecond);
    }

    /**
     * 获取日期中的某个数值
     *
     * @param date     日期
     * @param dateType 1：年，2：月，7：周，:5：日，11：时，12：分，13：秒，14：毫秒
     * @return
     */
    public static Integer getTime(Date date, int dateType) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    /**
     * 获取日期:年
     *
     * @param date 日期
     * @return 年份
     */
    public static Integer getYear(Date date) {
        return getTime(date, Calendar.YEAR);
    }

    /**
     * 获取日期:月
     *
     * @param date 日期
     * @return 月份
     */
    public static Integer getMonth(Date date) {
        return getTime(date, Calendar.MONTH);
    }

    /**
     * 获取日期:日
     *
     * @param date 日期
     * @return 日
     */
    public static Integer getDay(Date date) {
        return getTime(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期:星期几
     *
     * @param date
     * @return
     */
    public static Integer getWeek(Date date) {
        return getTime(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日期:时
     *
     * @param date 日期
     * @return 小时
     */
    public static Integer getHour(Date date) {
        return getTime(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期:分
     *
     * @param date 日期
     * @return 分钟
     */
    public static Integer getMinute(Date date) {
        return getTime(date, Calendar.MINUTE);
    }

    /**
     * 获取日期:秒
     *
     * @param date 日期
     * @return 秒
     */
    public static Integer getSecond(Date date) {
        return getTime(date, Calendar.SECOND);
    }

    /**
     * 获取日期:毫秒
     *
     * @param date 日期
     * @return 毫秒
     */
    public static Integer getMillisecond(Date date) {
        return getTime(date, Calendar.MILLISECOND);
    }

    /**
     * 当前日期:年
     *
     * @return
     */
    public static Integer getYear() {
        return getYear(getDate());
    }

    /**
     * 当前日期:月
     *
     * @return
     */
    public static Integer getMonth() {
        return getMonth(getDate());
    }

    /**
     * 当前日期:日
     *
     * @return
     */
    public static Integer getDay() {
        return getDay(getDate());
    }

    /**
     * 当前日期:星期几
     *
     * @return
     */
    public static Integer getWeek() {
        return getWeek(getDate());
    }

    /**
     * 当前日期:时
     *
     * @return
     */
    public static Integer getHour() {
        return getHour(getDate());
    }

    /**
     * 当前日期:分
     *
     * @return
     */
    public static Integer getMinute() {
        return getMinute(getDate());
    }

    /**
     * 当前日期:秒
     *
     * @return
     */
    public static Integer getSecond() {
        return getSecond(getDate());
    }

    /**
     * 当前日期:毫秒
     *
     * @return
     */
    public static Integer getMillisecond() {
        return getMillisecond(getDate());
    }

}
