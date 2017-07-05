package com.mishkaowner.appbase.ui.presenter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mishkaowner.appbase.ui.interactor.IFakeDataInteractor;
import com.mishkaowner.appbase.ui.view.IMainActivity;
import com.mishkaowner.appbase.ui.viewmodel.MainActivityViewModel;
import com.mishkaowner.baselibrary.ui.base.BaseAbstractPresenterWithVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Oak on 2017-06-08.
 */

public class MainActivityPresenter extends BaseAbstractPresenterWithVM<IMainActivity, MainActivityViewModel> implements IMainActivityPresenter {
    @Inject
    IFakeDataInteractor fakeDataInteractor;
    TestViewModel testViewModel;

    @Inject
    public MainActivityPresenter(IMainActivity view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        println("onCreate secure");
    }

    @Override
    public void onResume() {
        super.onResume();
        view.setViewListeners();
        println("onResume secure " + vm.getTestViewModel().getList().size());
    }

    @Override
    protected void onResumeWithFreshVM(MainActivityViewModel mainActivityViewModel) {
        println("onResumeWithFreshVM secure");
        //if(mainActivityViewModel.getTestViewModel() == null) {
            testViewModel = new TestViewModel();
            testViewModel.setName("Hello");
            for (int i = 0; i < 100; i++) {
                TestViewModelItem testViewModelItem = new TestViewModelItem();
                testViewModelItem.setName("dsafsfd");
                for (int j = 0; j < 100; j++) {
                    TestViewModelItemItem itemItem = new TestViewModelItemItem();
                    itemItem.setName1("asdf " + j);
                    itemItem.setName2("fsad " + j);
                    itemItem.setName3("dd " + j);
                    testViewModelItem.getList().add(itemItem);
                }
                testViewModel.getList().add(testViewModelItem);
            }
            mainActivityViewModel.setTestViewModel(testViewModel);
        //}
    }

    @Override
    protected void onResumeWithRestoredVM(MainActivityViewModel mainActivityViewModel) {
        println("onResumeWithRestoredVM secure");
        view.setQueryText(vm.getQuery());
        view.setResultText(vm.getResult());
        println("sdf " + vm.getTestViewModel().getList().size());
    }

    @Override
    public void onRetrieveBtClicked() {
        view.showProgress();
        fakeDataInteractor.retrieveSavedData()
                .defaultIfEmpty("")
                .compose(applyMaybeSchedulers())
                .subscribe(data -> {
                    vm.setResult("I Found this! : " + data);
                    view.setResultText(vm.getResult());
                    view.hideProgress();
                });
    }

    @Override
    public void onSubmitBtClicked() {
        view.showProgress();
        fakeDataInteractor.saveData(vm.getQuery())
                .compose(applyCompletableSchedulers())
                .subscribe(() -> {
                    vm.setResult("Saved Data!");
                    view.setResultText(vm.getResult());
                    vm.setQuery("");
                    view.setQueryText(vm.getQuery());
                    view.hideProgress();
                });
    }

    @Override
    public void onQueryChanged(String query) {
        vm.setQuery(query);
    }

    @Override
    protected String getSecureKey() {
        return fakeDataInteractor.getSecureKey().blockingFirst(NO_SECURITY_KEY);
    }

    public class TestViewModel {
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

    public class TestViewModelItem {
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

    public class TestViewModelItemItem {
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
