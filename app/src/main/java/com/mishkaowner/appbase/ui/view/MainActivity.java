package com.mishkaowner.appbase.ui.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mishkaowner.appbase.MyApp;
import com.mishkaowner.appbase.R;
import com.mishkaowner.appbase.di.module.MainActivityModule;
import com.mishkaowner.appbase.ui.presenter.IMainActivityPresenter;
import com.mishkaowner.baselibrary.ui.base.BaseAbstractActivity;
import com.mishkaowner.baselibrary.ui.base.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends BaseAbstractActivity implements IMainActivity {
    @Inject
    IMainActivityPresenter presenter;
    @BindView(R.id.queryEdit)
    EditText queryEdit;
    @BindView(R.id.resultTxt)
    TextView resultTxt;
    @BindView(R.id.progressView)
    View progressView;

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
        return presenter;
    }

    @OnClick(R.id.retrieveBt)
    public void onRetrieveBtClicked() {
        presenter.onRetrieveBtClicked();
    }

    @OnClick(R.id.submitBt)
    public void onSubmitBtClicked() {
        presenter.onSubmitBtClicked();
    }

    @OnTextChanged(R.id.queryEdit)
    public void onQueryChanged(CharSequence charSequence) {
        presenter.onQueryChanged(charSequence.toString());
    }

    @Override
    public void setResultText(String data) {
        resultTxt.setText(data);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setQueryText(String query) {
        queryEdit.setText(query);
    }
}