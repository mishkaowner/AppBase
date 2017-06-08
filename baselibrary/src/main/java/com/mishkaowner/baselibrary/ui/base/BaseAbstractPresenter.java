package com.mishkaowner.baselibrary.ui.base;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LKPC0008 on 2017-03-20.
 */

public abstract class BaseAbstractPresenter<V extends BaseView> implements BasePresenter {
    protected V view;
    protected CompositeDisposable disposeOnPause, disposeOnDestroy;

    public BaseAbstractPresenter(V view) {
        this.view = view;
    }

    @Override
    public void onResume() {
        disposeOnPause = new CompositeDisposable();
    }

    @Override
    public void onPause() {
        if(disposeOnPause != null && !disposeOnPause.isDisposed()) {
            disposeOnPause.dispose();
        }
    }

    @Override
    public void onSave() {
    }

    @Override
    public void onRestore() {
    }

    @Override
    public void onCreate() {
        disposeOnDestroy = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        if(disposeOnDestroy != null && !disposeOnDestroy.isDisposed()) {
            disposeOnDestroy.dispose();
        }
    }

    public static final <T> ObservableTransformer<T, T> applySchedulers(){
        return upstream -> upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    protected void println(String value) {
        System.out.println(value);
    }



}
