package com.mishkaowner.appbase.ui.presenter;

import com.mishkaowner.appbase.ui.view.IMainActivity;
import com.mishkaowner.baselibrary.ui.base.BaseAbstractPresenter;

import javax.inject.Inject;

/**
 * Created by Oak on 2017-06-08.
 */

public class MainActivityPresenter extends BaseAbstractPresenter<IMainActivity> implements IMainActivityPresenter {
    @Inject
    public MainActivityPresenter(IMainActivity view) {
        super(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        view.setText("Hello World from Presenter!");
    }
}
