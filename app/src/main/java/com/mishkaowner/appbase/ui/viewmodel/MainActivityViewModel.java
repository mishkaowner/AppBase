package com.mishkaowner.appbase.ui.viewmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mishkaowner.appbase.ui.presenter.MainActivityPresenter;
import com.mishkaowner.baselibrary.ui.base.BaseViewModel;

/**
 * Created by Oak on 2017-06-14.
 */

public class MainActivityViewModel implements BaseViewModel {
    @Expose
    @SerializedName("query")
    private String query = null;
    @Expose
    @SerializedName("result")
    private String result = null;
    @Expose
    @SerializedName("test")
    private MainActivityPresenter.TestViewModel testViewModel = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public MainActivityPresenter.TestViewModel getTestViewModel() {
        return testViewModel;
    }

    public void setTestViewModel(MainActivityPresenter.TestViewModel testViewModel) {
        this.testViewModel = testViewModel;
    }
}
