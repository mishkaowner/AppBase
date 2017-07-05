package com.mishkaowner.baselibrary.util;

/**
 * Created by jhkim on 17. 7. 5.
 */

public interface ICipherUtil {
    String decode(String value, String pass);
    String encode(String value, String pass);
}
