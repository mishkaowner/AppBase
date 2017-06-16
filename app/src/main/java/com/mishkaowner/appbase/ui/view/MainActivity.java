package com.mishkaowner.appbase.ui.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mishkaowner.appbase.MyApp;
import com.mishkaowner.appbase.R;
import com.mishkaowner.appbase.di.module.MainActivityModule;
import com.mishkaowner.appbase.ui.presenter.IMainActivityPresenter;
import com.mishkaowner.baselibrary.ui.base.BaseAbstractActivity;
import com.mishkaowner.baselibrary.ui.base.BasePresenter;
import com.mishkaowner.baselibrary.util.ISharedDataEditor;

import java.util.ArrayList;
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
    @Inject
    ISharedDataEditor sharedDataEditor;

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


        test();
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

    private void test() {
    }

    class TestViewModel {
        @Expose
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Expose
        @SerializedName("list")
        private List<TestViewModelItem> list = new ArrayList();

        public List<TestViewModelItem> getList() {
            return list;
        }

        public void setList(List<TestViewModelItem> list) {
            this.list = list;
        }
    }

    class TestViewModelItem {
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("list")
        private List<TestViewModelItemItem> list = new ArrayList();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TestViewModelItemItem> getList() {
            return list;
        }

        public void setList(List<TestViewModelItemItem> list) {
            this.list = list;
        }
    }

    class TestViewModelItemItem {
        @Expose
        @SerializedName("name1")
        private String name1;
        @Expose
        @SerializedName("name2")
        private String name2;
        @Expose
        @SerializedName("name3")
        private String name3;

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public String getName3() {
            return name3;
        }

        public void setName3(String name3) {
            this.name3 = name3;
        }
    }
}