package com.mishkaowner.baselibrary.util;

public interface ICipherUtil {
    String decode(String value, String pass);
    String encode(String value, String pass);
}
