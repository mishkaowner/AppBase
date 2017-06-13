package com.mishkaowner.appbase.ui.presenter;

import com.mishkaowner.baselibrary.ui.base.BasePresenter;

/**
 * Created by Oak on 2017-06-08.
 */

public interface IMainActivityPresenter extends BasePresenter {
    void onRetrieveBtClicked();

    void onSubmitBtClicked();

    void onQueryChanged(String query);
}
