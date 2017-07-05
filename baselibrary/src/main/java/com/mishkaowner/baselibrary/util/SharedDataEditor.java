package com.mishkaowner.baselibrary.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedDataEditor implements ISharedDataEditor {
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    ICipherUtil cipherUtil;

    @Inject
    public SharedDataEditor() {
    }

    @Override
    public void setData(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public String getData(String key) {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public void removeData(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    @Override
    public void setSecureData(String key, String value, String password) {
        setData(key, cipherUtil.encode(value, password));
    }

    @Override
    public String getSecureData(String key, String password) {
        String retreivedData = sharedPreferences.getString(key, "");
        return TextCompat.isEmpty(retreivedData) ? "" : cipherUtil.decode(retreivedData, password);
    }
}
