package com.mishkaowner.baselibrary.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LKPC0008 on 2017-03-20.
 */

public abstract class BaseAbstractActivity extends AppCompatActivity implements BaseView {
    protected static final int NO_LAYOUT = -1234;
    protected CompositeDisposable disposeOnPause, disposeOnDestroy;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposeOnDestroy = new CompositeDisposable();
        if (getLayoutId() != NO_LAYOUT) {
            setContentView(getLayoutId());
            unbinder = ButterKnife.bind(this);
        }
        inject();
        if (savedInstanceState != null) {
            if (getPresenter() != null) {
                getPresenter().onRestore();
            }
        } else {
            if (getPresenter() != null) {
                getPresenter().onCreate();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        disposeOnPause = new CompositeDisposable();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().onPause();
        }
        if (disposeOnPause != null && !disposeOnPause.isDisposed()) {
            disposeOnPause.dispose();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getPresenter() != null) {
            getPresenter().onSave();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (disposeOnDestroy != null && !disposeOnDestroy.isDisposed()) {
            disposeOnDestroy.dispose();
        }
    }

    public static final <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    protected void println(String value) {
        System.out.println(value);
    }
}
