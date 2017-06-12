package com.mishkaowner.appbase;

import android.content.Context;

import com.mishkaowner.appbase.di.component.DaggerMyAppComponent;
import com.mishkaowner.appbase.di.component.MyAppComponent;
import com.mishkaowner.appbase.di.module.MyAppModule;
import com.mishkaowner.baselibrary.BaseApp;
import com.mishkaowner.baselibrary.di.component.DaggerBaseAppComponent;
import com.mishkaowner.baselibrary.di.module.BaseAppModule;
import com.mishkaowner.baselibrary.util.SharedDataEditor;

import javax.inject.Inject;


/**
 * Created by Oak on 2017-06-08.
 */

public class MyApp extends BaseApp {
    private MyAppComponent myAppComponent;
    @Inject
    SharedDataEditor sharedDataEditor;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppComponent = DaggerMyAppComponent.builder()
                .baseAppComponent(DaggerBaseAppComponent.builder().baseAppModule(new BaseAppModule(MyApp.this)).build())
                .myAppModule(new MyAppModule(this))
                .build();
        myAppComponent.inject(this);
        //sharedDataEditor.setData("Test", "Hello");
        System.out.println("Saved data? " + sharedDataEditor.getData("Test"));
    }

    public static MyAppComponent get(Context context){
        return ((MyApp)context.getApplicationContext()).getMyAppComponent();
    }

    public MyAppComponent getMyAppComponent() {
        return myAppComponent;
    }
}
