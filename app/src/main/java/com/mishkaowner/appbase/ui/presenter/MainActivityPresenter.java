package com.mishkaowner.appbase.ui.presenter;

import com.mishkaowner.appbase.ui.interactor.IFakeDataInteractor;
import com.mishkaowner.appbase.ui.view.IMainActivity;
import com.mishkaowner.appbase.ui.viewmodel.MainActivityViewModel;
import com.mishkaowner.baselibrary.ui.base.BaseAbstractPresenterWithVM;

import javax.inject.Inject;

/**
 * Created by Oak on 2017-06-08.
 */

public class MainActivityPresenter extends BaseAbstractPresenterWithVM<IMainActivity, MainActivityViewModel> implements IMainActivityPresenter {
    @Inject
    IFakeDataInteractor fakeDataInteractor;

    @Inject
    public MainActivityPresenter(IMainActivity view) {
        super(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        view.setViewListeners();
    }

    @Override
    protected void onResumeWithFreshVM(MainActivityViewModel mainActivityViewModel) {
    }

    @Override
    protected void onResumeWithRestoredVM(MainActivityViewModel mainActivityViewModel) {
        view.setQueryText(vm.getQuery());
        view.setResultText(vm.getResult());
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
}
