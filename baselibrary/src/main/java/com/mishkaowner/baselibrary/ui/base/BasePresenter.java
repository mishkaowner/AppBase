package com.mishkaowner.baselibrary.ui.base;

/**
 * Created by LKPC0008 on 2017-03-20.
 */

public interface BasePresenter {
    void onCreate();
    void onDestroy();
    void onSave();
    void onRestore();
    void onResume();
    void onPause();
}
