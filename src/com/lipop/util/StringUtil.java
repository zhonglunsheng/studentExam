package com.lipop.util;

public class StringUtil {
    public static boolean isNotEmpty(String str){
        if (str!=null&&!str.equals("")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean IsEmpty(String str){
        if (str==null||str.equals("")){
            return true;
        }else{
            return false;
        }
    }
}
