package com.mishkaowner.baselibrary.util;

public class TextCompat {
    public static boolean isEmpty(String string){
        return string == null || string.length() == 0;
    }
    public static boolean isBlank(String string) {
        return string == null || string.length() == 0 || string.replaceAll(" ", "").length() == 0;
    }
    public static boolean equal(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }
}
