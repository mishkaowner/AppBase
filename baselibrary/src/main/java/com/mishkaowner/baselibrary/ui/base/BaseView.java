package com.mishkaowner.baselibrary.ui.base;

/**
 * Created by LKPC0008 on 2017-03-20.
 */

public interface BaseView {
    int getLayoutId();
    void inject();
    BasePresenter getPresenter();
}
