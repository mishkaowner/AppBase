package com.mishkaowner.baselibrary.di.module;

import android.content.Context;

import com.mishkaowner.baselibrary.BaseApp;
import com.mishkaowner.baselibrary.di.scope.LibraryScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oak on 2017-06-08.
 */
@Module
public class BaseAppModule {
    private BaseApp app;

    public BaseAppModule(BaseApp app) {
        this.app = app;
    }

    @Provides
    @LibraryScope
    BaseApp providesBaseApp(){
        return app;
    }

    @Provides
    @LibraryScope
    @Named("AppContext")
    Context providesAppContext(BaseApp app) {
        return app.getApplicationContext();
    }
}
