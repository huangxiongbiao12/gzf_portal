package com.gzf.util;

import java.util.regex.Pattern;

/**
 * Created by yangweiguang on 2017/6/21.
 */
public class StringUtil {
    public static String firstToUpper(String str){
        if(str==null || str.length()==0) return "";
        return str.substring(0,1).toUpperCase() + str.substring(1) ;
    }
    public static String firstToLower(String str){
        if(str==null || str.length()==0) return "";
        return str.substring(0,1).toLowerCase() + str.substring(1) ;
    }
    public static boolean isNullOrEmpty(String str){
        if(str == null || str.length() == 0){
            return true;
        }
        return false;
    }

    //判断手机号码是不是合法的
    public static boolean checkMobile(String mobile){
        String regex="^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";
        return Pattern.matches(regex, mobile);
    }
}
