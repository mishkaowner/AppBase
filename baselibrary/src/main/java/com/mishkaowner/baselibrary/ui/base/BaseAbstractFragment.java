package com.mishkaowner.baselibrary.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseAbstractFragment extends Fragment implements BaseView {
    protected static final int NO_LAYOUT = -1234;
    protected CompositeDisposable disposeOnPause, disposeOnDestroy;
    protected Unbinder unbinder;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && getPresenter() != null) {
            getPresenter().onRestore();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() != NO_LAYOUT && inflater != null && container != null) {
            return inflater.inflate(getLayoutId(), container, false);
        } else {
            return null;
        }
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        disposeOnDestroy = new CompositeDisposable();
        inject();
        if (view != null) {
            unbinder = ButterKnife.bind(this, view);
        }
        if (getPresenter() != null) {
            getPresenter().onCreate();
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
    public void onDestroyView() {
        super.onDestroyView();
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

    @Override
    public void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getPresenter() != null) {
            getPresenter().onSave();
        }
    }

    protected void println(String value) {
        System.out.println(value);
    }
}
