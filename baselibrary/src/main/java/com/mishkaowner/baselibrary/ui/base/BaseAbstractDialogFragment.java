package com.mishkaowner.baselibrary.ui.base;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseAbstractDialogFragment extends DialogFragment implements BaseView {
    protected static final int NO_LAYOUT = -1234;
    protected CompositeDisposable disposeOnPause, disposeOnDestroy;
    protected Unbinder unbinder;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog =  super.onCreateDialog(savedInstanceState);
        try {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        } catch (Exception e) {
        }
        return dialog;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        final Fragment parent = getParentFragment();
        if (!enter && parent != null && parent.isRemoving()) {
            Animation doNothingAnim = new AlphaAnimation(1, 1);
            doNothingAnim.setDuration(getNextAnimationDuration(parent, 250));
            return doNothingAnim;
        } else {
            try {
                Animation anim = super.onCreateAnimation(transit, enter, nextAnim);
                if (anim == null && nextAnim != 0) {
                    anim = AnimationUtils.loadAnimation(getContext(), nextAnim);
                }
                if (anim != null) {
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }
                    });
                }
                return anim;
            } catch (Exception e) {
                return super.onCreateAnimation(transit, enter, nextAnim);
            }
        }
    }

    private static long getNextAnimationDuration(Fragment fragment, long defValue) {
        try {
            Field nextAnimField = Fragment.class.getDeclaredField("mNextAnim");
            nextAnimField.setAccessible(true);
            int nextAnimResource = nextAnimField.getInt(fragment);
            Animation nextAnim = AnimationUtils.loadAnimation(fragment.getActivity(), nextAnimResource);
            return (nextAnim == null) ? defValue : nextAnim.getDuration();
        } catch (NoSuchFieldException|IllegalAccessException|Resources.NotFoundException ex) {
            System.out.println("Unable to load next animation from parent." + ex.getMessage());
            return defValue;
        }
    }

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
        if (getLayoutId() != NO_LAYOUT && inflater != null) {
            return inflater.inflate(getLayoutId(), null);
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

    public abstract void close();
}
