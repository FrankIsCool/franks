package com.franks.util.age;

import com.franks.util.date.DateProperties;
import com.franks.util.date.DateUtil;
import com.franks.util.date.StrDateUtil;
import com.franks.util.valid.IDCardValidUtil;
import com.franks.util.valid.ValidUtils;

import java.util.Date;

public class AgeUtil {
    /**
     * 根据出生日期，获取年龄
     *
     * @param birthDate 出生日期：格式如：1992-05-24
     * @return java.lang.Integer
     * @Author frank
     * @Date 2021/12/27 11:26
     */
    public static Integer getAge(String birthDate) {
        if (!ValidUtils.isBirthday(birthDate)) {
            throw new RuntimeException("出生日期格式错误");
        }
        return DateUtil.getYear() - DateUtil.getYear(StrDateUtil.toDate(birthDate, DateProperties.DATE_YEAR_MONTH_DAY));
    }

    /**
     * 根据出生日期，获取年龄
     *
     * @param birthDate 出生日期
     * @return java.lang.Integer
     * @Author frank
     * @Date 2021/12/27 11:26
     */
    public static Integer getAge(Date birthDate) {
        return DateUtil.getYear() - DateUtil.getYear(birthDate);
    }

    /**
     * 根据出生日期，获取年龄
     *
     * @param birthDate 出生日期：格式如：19920524
     * @return java.lang.Integer
     * @Author frank
     * @Date 2021/12/27 11:26
     */
    public static Integer getAge2(String birthDate) {
        birthDate = birthDate.replace("-", "");
        if (!ValidUtils.isBirthday2(birthDate)) {
            throw new RuntimeException("出生日期格式错误");
        }
        return getAge(birthDate.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3"));
    }

    /**
     * 根据身份证，获取年龄
     *
     * @param idCard 身份证
     * @return java.lang.Integer
     * @Author frank
     * @Date 2021/12/27 11:26
     */
    public static Integer getAgeByIdCard(String idCard) {
        return getAge2(IDCardValidUtil.getBirthDate(idCard));
    }
}
