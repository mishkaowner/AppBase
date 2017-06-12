package com.mishkaowner.baselibrary.ui.base;

public interface BaseView {
    int getLayoutId();
    void inject();
    BasePresenter getPresenter();
}
