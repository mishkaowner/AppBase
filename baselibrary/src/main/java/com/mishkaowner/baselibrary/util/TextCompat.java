package com.mishkaowner.baselibrary.util;

/**
 * Created by jhkim on 17. 4. 21.
 */

public class TextCompat {
    public static boolean isEmpty(String string){
        return string == null || string.length() == 0;
    }
    public static boolean isBlank(String string) {
        return string == null || string.length() == 0 || string.replaceAll(" ", "").length() == 0;
    }
}
