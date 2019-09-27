package com.vainglory.util;

public class StringUtils {
    public static boolean isEmpty(String str){
        if(str==null || str.trim().length()==0){
            return true;
        }
        return false;
    }

}