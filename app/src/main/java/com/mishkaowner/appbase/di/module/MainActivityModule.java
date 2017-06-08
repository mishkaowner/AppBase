package com.mishkaowner.appbase.di.module;

import com.mishkaowner.appbase.ui.view.IMainActivity;
import com.mishkaowner.appbase.ui.presenter.IMainActivityPresenter;
import com.mishkaowner.appbase.ui.view.MainActivity;
import com.mishkaowner.appbase.ui.presenter.MainActivityPresenter;
import com.mishkaowner.baselibrary.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oak on 2017-06-08.
 */
@Module
public class MainActivityModule {
    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity providesActivity(){
        return activity;
    }

    @Provides
    @ActivityScope
    IMainActivity providesView(MainActivity activity) {
        return activity;
    }

    @Provides
    @ActivityScope
    IMainActivityPresenter providesPresenter(MainActivityPresenter presenter) {
        return presenter;
    }
}
