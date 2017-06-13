package com.mishkaowner.appbase.ui.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mishkaowner.appbase.MyApp;
import com.mishkaowner.appbase.R;
import com.mishkaowner.appbase.di.module.MainActivityModule;
import com.mishkaowner.appbase.ui.presenter.IMainActivityPresenter;
import com.mishkaowner.baselibrary.ui.base.BaseAbstractActivity;
import com.mishkaowner.baselibrary.ui.base.BasePresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import io.reactivex.Observable;

public class MainActivity extends BaseAbstractActivity implements IMainActivity {
    @Inject
    IMainActivityPresenter presenter;
    @BindView(R.id.queryEdit)
    EditText queryEdit;
    @BindViews({R.id.retrieveBt, R.id.submitBt})
    List<View> bts;
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

    @Override
    public void setViewListeners() {
        disposeOnPause.add(Observable.fromIterable(bts)
                .flatMap(v -> RxView.clicks(v).map(o -> v.getId()))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(id -> {
                    if (id == R.id.retrieveBt) {
                        presenter.onRetrieveBtClicked();
                    } else if (id == R.id.submitBt) {
                        presenter.onSubmitBtClicked();
                    }
                }));

        disposeOnPause.add(RxTextView.textChanges(queryEdit)
                .map(CharSequence::toString)
                .subscribe(query -> presenter.onQueryChanged(query)));
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