package com.franks.util.valid;

import com.franks.util.date.DateProperties;
import com.franks.util.date.DateUtil;
import com.franks.util.date.StrDateUtil;
import com.franks.util.empty.EmptyUtil;
import com.franks.util.exception.ApiException;

/**
 * 身份证号校验
 *
 * @author frank
 * @module
 * @date 2021/9/27 16:02
 */
public class IDCardValidUtil {
    //地区编码
    public static final String AREA_CODE = "(11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)";
    //身份证最后一位
    public static final String[] WF = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
    //权重
    public static final Integer[] CHECK_CODE = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 校验是否满足身份证号
     *
     * @param idCard 身份证号
     * @return
     * @Author frank
     * @Date 2021/9/27 15:58
     */

    public static Boolean isIDCard(String idCard) {
        if (EmptyUtil.isEmpty(idCard)) {
            return Boolean.FALSE;
        }
        if ((idCard = idCard.trim()).length() != 15 && idCard.length() != 18) {
            return Boolean.FALSE;
        }
        if (idCard.length() == 15) {
            idCard = idCard.substring(0, 6) + "19" + idCard.substring(6, 15);
        }
        if (!ValidUtils.isPositiveInteger(idCard.substring(0, 17))) {
            return Boolean.FALSE;
        }
        if (!ValidUtils.valid(AREA_CODE, idCard.substring(0, 2))) {
            return Boolean.FALSE;
        }
        if (!DateUtil.toStr(StrDateUtil.toDate(idCard.substring(6, 14), DateProperties.DATE_2_YEAR_MONTH_DAY), DateProperties.DATE_2_YEAR_MONTH_DAY).equals(idCard.substring(6, 14))) {
            return Boolean.FALSE;
        }
        if (idCard.length() == 18 && !idCard.substring(17, 18).equalsIgnoreCase(getLastValue(idCard))) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 根据身份证获取出生日期
     *
     * @param idCard
     * @return
     * @Author frank
     * @Date 2021/9/27 16:24
     */
    public static String getBirthDate(String idCard) {
        String birthDate = DateUtil.toStr(StrDateUtil.toDate(idCard.substring(6, 14), DateProperties.DATE_2_YEAR_MONTH_DAY), DateProperties.DATE_2_YEAR_MONTH_DAY);
        if (!birthDate.equals(idCard.substring(6, 14))) {
            throw new ApiException("身份格式错误");
        }
        return birthDate;
    }

    /**
     * 获取最后一位
     *
     * @param idCard 身份证号
     * @return
     * @Author frank
     * @Date 2021/9/27 15:59
     */
    private static String getLastValue(String idCard) {
        int theLastOne = 0;
        for (int i = 0; i < 17; i++) {
            theLastOne += Integer.parseInt(String.valueOf(idCard.charAt(i))) * CHECK_CODE[i];
        }
        return WF[theLastOne % 11];
    }
}

