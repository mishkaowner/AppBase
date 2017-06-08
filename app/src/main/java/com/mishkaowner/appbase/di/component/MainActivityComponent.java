package com.mishkaowner.appbase.di.component;

import com.mishkaowner.appbase.di.module.MainActivityModule;
import com.mishkaowner.appbase.ui.view.MainActivity;
import com.mishkaowner.baselibrary.di.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Oak on 2017-06-08.
 */
@ActivityScope
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
