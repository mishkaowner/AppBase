package com.mishkaowner.appbase;

import android.content.Context;

import com.mishkaowner.appbase.di.component.DaggerMyAppComponent;
import com.mishkaowner.appbase.di.component.MyAppComponent;
import com.mishkaowner.appbase.di.module.MyAppModule;
import com.mishkaowner.baselibrary.BaseApp;
import com.mishkaowner.baselibrary.di.component.DaggerBaseAppComponent;
import com.mishkaowner.baselibrary.di.module.BaseAppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Oak on 2017-06-08.
 */

public class MyApp extends BaseApp {
    private MyAppComponent myAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        myAppComponent = DaggerMyAppComponent.builder()
                .baseAppComponent(DaggerBaseAppComponent.builder().baseAppModule(new BaseAppModule(this)).build())
                .myAppModule(new MyAppModule(this))
                .build();
        myAppComponent.inject(this);
    }

    public static MyAppComponent get(Context context){
        return ((MyApp)context.getApplicationContext()).getMyAppComponent();
    }

    public MyAppComponent getMyAppComponent() {
        return myAppComponent;
    }
}
