package com.mishkaowner.baselibrary.util;

public interface ISharedDataEditor {
    void setData(String key, String value);
    String getData(String key);
    void removeData(String key);
    void setSecureData(String key, String value, String password);
    String getSecureData(String key, String password);
}
