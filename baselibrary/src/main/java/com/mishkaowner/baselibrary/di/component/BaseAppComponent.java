package com.mishkaowner.baselibrary.di.component;

import com.mishkaowner.baselibrary.BaseApp;
import com.mishkaowner.baselibrary.di.module.BaseAppModule;
import com.mishkaowner.baselibrary.di.scope.LibraryScope;


import dagger.Component;

/**
 * Created by Oak on 2017-06-08.
 */
@LibraryScope
@Component(modules = {BaseAppModule.class})
public interface BaseAppComponent {
    void inject(BaseApp baseApp);
}
