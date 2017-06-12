package com.mishkaowner.baselibrary.ui.base;

public interface BasePresenter {
    void onCreate();
    void onDestroy();
    void onSave();
    void onRestore();
    void onResume();
    void onPause();
}
