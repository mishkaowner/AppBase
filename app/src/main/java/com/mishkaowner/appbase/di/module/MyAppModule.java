package com.mishkaowner.appbase.di.module;

import com.mishkaowner.appbase.MyApp;
import com.mishkaowner.baselibrary.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oak on 2017-06-08.
 */
@Module
public class MyAppModule {
    private MyApp myApp;

    public MyAppModule(MyApp myApp) {
        this.myApp = myApp;
    }

    @Provides
    @ApplicationScope
    MyApp providesMyApp(){
        return myApp;
    }
}
