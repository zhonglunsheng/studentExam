package com.lipop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 时间转字符串
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        String result="";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date!=null){
            result= sdf.format(date);
        }
        return result;
    }

    /**
     * 字符串转时间
     * @param str
     * @param format 转换格式
     * @return
     */
    public static Date formatString(String str,String format){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
