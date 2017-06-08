package com.mishkaowner.appbase.di.component;

import com.mishkaowner.appbase.MyApp;
import com.mishkaowner.appbase.di.module.MainActivityModule;
import com.mishkaowner.appbase.di.module.MyAppModule;
import com.mishkaowner.baselibrary.di.scope.ApplicationScope;
import com.mishkaowner.baselibrary.di.component.BaseAppComponent;
import dagger.Component;

/**
 * Created by Oak on 2017-06-08.
 */
@ApplicationScope
@Component(dependencies = BaseAppComponent.class, modules = {MyAppModule.class})
public interface MyAppComponent {
    void inject(MyApp app);
    MainActivityComponent plus(MainActivityModule module);
}
