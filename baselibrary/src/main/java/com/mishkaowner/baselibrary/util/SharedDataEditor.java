package com.mishkaowner.baselibrary.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by jhkim on 17. 6. 7.
 */

public class SharedDataEditor {
    private SharedPreferences sharedPreferences;

    @Inject
    public SharedDataEditor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setData(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getData(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void removeData(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
