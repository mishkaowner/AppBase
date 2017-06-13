package com.mishkaowner.appbase.ui.view;

import com.mishkaowner.baselibrary.ui.base.BaseView;

/**
 * Created by Oak on 2017-06-08.
 */

public interface IMainActivity extends BaseView {
    void setViewListeners();

    void setResultText(String data);

    void hideProgress();

    void showProgress();

    void setQueryText(String query);
}
