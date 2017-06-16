package com.mishkaowner.baselibrary.util;

/**
 * Created by jhkim on 17. 6. 16.
 */

public interface ISharedDataEditor {
    void setData(String key, String value);
    String getData(String key);
    void removeData(String key);
    void setSecureData(String key, String value, String password);
    String getSecureData(String key, String password);
}
