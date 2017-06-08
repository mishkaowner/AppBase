package com.mishkaowner.appbase;

import android.app.Application;
import android.content.Context;

import com.mishkaowner.appbase.di.component.*;
import com.mishkaowner.appbase.di.module.MyAppModule;
import com.mishkaowner.baselibrary.di.component.DaggerBaseAppComponent;


/**
 * Created by Oak on 2017-06-08.
 */

public class MyApp extends Application {
    private MyAppComponent myAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppComponent = DaggerMyAppComponent.builder().baseAppComponent(DaggerBaseAppComponent.create()).myAppModule(new MyAppModule(this)).build();
    }

    public static MyAppComponent get(Context context){
        return ((MyApp)context.getApplicationContext()).getMyAppComponent();
    }

    public MyAppComponent getMyAppComponent() {
        return myAppComponent;
    }
}
