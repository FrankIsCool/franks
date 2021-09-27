package com.franks.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author frank
 * @module
 * @date 2021/9/4 14:50
 */
public class StrDateUtil {

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
     * string转成Instant
     *
     * @param date 时间
     * @param format 时间格式
     * @return
     */
    public static Instant toInstant(String date, String format) {
        return toDate(date,format).toInstant();
    }
    /**
     * string转成Instant
     *
     * @param date 时间
     * @return
     */
    public static Instant toInstant(String date) {
        return toDate(date).toInstant();
    }
    /**
     * string转成Long
     *
     * @param date 时间
     * @param format 时间格式
     * @return
     */
    public static long toLong(String date, String format) {
        return toDate(date,format).getTime();
    }
    /**
     * string转成Long
     *
     * @param date 时间
     * @return
     */
    public static long toLong(String date) {
        return toDate(date).getTime();
    }
    /**
     * string转成Timestamp
     *
     * @param date 时间
     * @param format 时间格式
     * @return
     */
    public static Timestamp toTimestamp(String date, String format) {
        return new Timestamp(toDate(date,format).getTime());
    }
    /**
     * string转成Timestamp
     *
     * @param date 时间
     * @return
     */
    public static Timestamp toTimestamp(String date) {
        return new Timestamp(toDate(date).getTime());
    }
    /**
     * 获取当前系统时间(原始格式)-格式化
     *
     * @param format
     * @return
     */
    public static String getDate(String format) {
        return new SimpleDateFormat(format).format(DateUtil.getDate());
    }
    /**
     * 获取当前系统时间(原始格式)-格式化
     *
     * @return
     */
    public static String getDate() {
        return new SimpleDateFormat(DateProperties.DATE_1_ALL).format(DateUtil.getDate());
    }
    /**
     * 某个时间戳增加时间
     *
     * @param date  日期
     * @param times 增加时间
     * @return 返回增加后的日期
     */
    public static Date addTime(String date, int... times) {
        return DateUtil.addTime(toDate(date), times[0], times[1], times[2], times[3], times[4], times[5], times[6]);
    }
    /**
     * 某个时间戳增加时间
     *
     * @param date  日期
     * @param times 增加时间
     * @return 返回增加后的日期
     */
    public static Date addTime(String date, String format, int... times) {
        return DateUtil.addTime(toDate(date,format), times[0], times[1], times[2], times[3], times[4], times[5], times[6]);
    }
    /**
     * 某个日期增加年
     *
     * @param date  日期
     * @param years 增加年数值
     * @return 返回增加后的日期
     */
    public static Date addYear(String date, String format,int years) {
        return addTime(date,format, years);
    }

    /**
     * 某个日期增加月
     *
     * @param date   日期
     * @param months 增加月数值
     * @return 返回增加后的日期
     */
    public static Date addMonth(String date,String format, int months) {
        return addTime(date, format,0, months);
    }

    /**
     * 某个日期增加日
     *
     * @param date 日期
     * @param days 增加天数值
     * @return 返回增加后的日期
     */
    public static Date addDay(String date,String format, int days) {
        return addTime(date, format,0, 0, days);
    }

    /**
     * 某个日期增加小时
     *
     * @param date  日期
     * @param hours 增加小时数值
     * @return 返回增加后的日期
     */
    public static Date addHour(String date,String format, int hours) {
        return addTime(date, format,0, 0, 0, hours);
    }

    /**
     * 某个日期增加分钟
     *
     * @param date    日期
     * @param minutes 增加分钟数值
     * @return 返回增加后的日期
     */
    public static Date addMinute(String date,String format, int minutes) {
        return addTime(date, format,0, 0, 0, 0, minutes);
    }

    /**
     * 某个日期增加秒
     *
     * @param date    日期
     * @param seconds 增加秒数值
     * @return 返回增加后的日期
     */
    public static Date addSecond(String date,String format, int seconds) {
        return addTime(date, format,0, 0, 0, 0, 0, seconds);
    }

    /**
     * 某个日期增加毫秒
     *
     * @param date        日期
     * @param milliSecond 增加毫秒数值
     * @return 返回增加后的日期
     */
    public static Date addMilliSecond(String date,String format, int milliSecond) {
        return addTime(date,format, 0, 0, 0, 0, 0, 0, milliSecond);
    }
    /**
     * 某个日期增加年
     *
     * @param date  日期
     * @param years 增加年数值
     * @return 返回增加后的日期
     */
    public static Date addYear(String date, int years) {
        return addTime(date, years);
    }

    /**
     * 某个日期增加月
     *
     * @param date   日期
     * @param months 增加月数值
     * @return 返回增加后的日期
     */
    public static Date addMonth(String date, int months) {
        return addTime(date, 0, months);
    }

    /**
     * 某个日期增加日
     *
     * @param date 日期
     * @param days 增加天数值
     * @return 返回增加后的日期
     */
    public static Date addDay(String date, int days) {
        return addTime(date, 0, 0, days);
    }

    /**
     * 某个日期增加小时
     *
     * @param date  日期
     * @param hours 增加小时数值
     * @return 返回增加后的日期
     */
    public static Date addHour(String date, int hours) {
        return addTime(date, 0, 0, 0, hours);
    }

    /**
     * 某个日期增加分钟
     *
     * @param date    日期
     * @param minutes 增加分钟数值
     * @return 返回增加后的日期
     */
    public static Date addMinute(String date, int minutes) {
        return addTime(date, 0, 0, 0, 0, minutes);
    }

    /**
     * 某个日期增加秒
     *
     * @param date    日期
     * @param seconds 增加秒数值
     * @return 返回增加后的日期
     */
    public static Date addSecond(String date, int seconds) {
        return addTime(date, 0, 0, 0, 0, 0, seconds);
    }

    /**
     * 某个日期增加毫秒
     *
     * @param date        日期
     * @param milliSecond 增加毫秒数值
     * @return 返回增加后的日期
     */
    public static Date addMilliSecond(String date, int milliSecond) {
        return addTime(date, 0, 0, 0, 0, 0, 0, milliSecond);
    }
    /**
     * 获取日期:年
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getYear(String data, String format) {
        return DateUtil.getYear(toDate(data, format));
    }


    /**
     * 获取日期:月
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getMonth(String data, String format) {
        return DateUtil.getMonth(toDate(data, format));
    }


    /**
     * 获取日期:日
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getDay(String data, String format) {
        return DateUtil.getDay(toDate(data, format));
    }


    /**
     * 获取日期:星期几
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getWeek(String data, String format) {
        return DateUtil.getWeek(toDate(data, format));
    }


    /**
     * 获取日期:时
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getHour(String data, String format) {
        return DateUtil.getHour(toDate(data, format));
    }


    /**
     * 获取日期:分
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getMinute(String data, String format) {
        return DateUtil.getMinute(toDate(data, format));
    }


    /**
     * 获取日期:秒
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getSecond(String data, String format) {
        return DateUtil.getSecond(toDate(data, format));
    }

    /**
     * 获取日期:毫秒
     *
     * @param data   时间字符串
     * @param format 时间格式
     * @return
     */
    public static Integer getMillisecond(String data, String format) {
        return DateUtil.getMillisecond(toDate(data, format));
    }

    /**
     * 获取日期:年
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getYear(String data) {
        return DateUtil.getYear(toDate(data));
    }


    /**
     * 获取日期:月
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getMonth(String data) {
        return DateUtil.getMonth(toDate(data));
    }


    /**
     * 获取日期:日
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getDay(String data) {
        return DateUtil.getDay(toDate(data));
    }


    /**
     * 获取日期:星期几
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getWeek(String data) {
        return DateUtil.getWeek(toDate(data));
    }


    /**
     * 获取日期:时
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getHour(String data) {
        return DateUtil.getHour(toDate(data));
    }


    /**
     * 获取日期:分
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getMinute(String data) {
        return DateUtil.getMinute(toDate(data));
    }


    /**
     * 获取日期:秒
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getSecond(String data) {
        return DateUtil.getSecond(toDate(data));
    }

    /**
     * 获取日期:毫秒
     *
     * @param data 时间字符串
     * @return
     */
    public static Integer getMillisecond(String data) {
        return DateUtil.getMillisecond(toDate(data));
    }
}
