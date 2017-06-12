package com.mishkaowner.appbase.ui.view;

import android.widget.TextView;

import com.mishkaowner.appbase.MyApp;
import com.mishkaowner.appbase.R;
import com.mishkaowner.appbase.di.module.MainActivityModule;
import com.mishkaowner.appbase.ui.presenter.IMainActivityPresenter;
import com.mishkaowner.baselibrary.ui.base.BaseAbstractActivity;
import com.mishkaowner.baselibrary.ui.base.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseAbstractActivity implements IMainActivity {
    @Inject
    IMainActivityPresenter presenter;
    @BindView(R.id.rt)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {
        MyApp.get(this).plus(new MainActivityModule(this)).inject(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setText(String s) {
        textView.setText(s);
    }
}
