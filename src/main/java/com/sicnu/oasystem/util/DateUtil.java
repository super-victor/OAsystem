package com.sicnu.oasystem.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName DateUtil
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/12/14 10:33
 * @Version v1.0
 */

public class DateUtil {

    /**
     * @MethodName isSameDay
     * @param date1
     * @param date2
     * @Description 判断两个date是否在同一天
     * @Author JohnTang
     * @Return boolean
     * @LastChangeDate 2020/12/15
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * @MethodName addDay
     * @param date
     * @param dayNum
     * @Description 将date增加daynum天
     * @Author JohnTang
     * @Return java.util.Date
     * @LastChangeDate 2020/12/15
     */
    public static Date addDay(Date date, int dayNum) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayNum);
        return cal.getTime();
    }

    /**
     * @MethodName subDay
     * @param date
     * @param dayNum
     * @Description 将date减少dayNum天
     * @Author JohnTang
     * @Return java.util.Date
     * @LastChangeDate 2020/12/15
     */
    public static Date subDay(Date date, int dayNum) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, - dayNum);
        return cal.getTime();
    }
}
