package com.mishkaowner.baselibrary.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.mishkaowner.baselibrary.di.scope.LibraryScope;
import com.mishkaowner.baselibrary.util.SharedDataEditor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedDataEditorModule {
    @LibraryScope
    @Provides
    SharedPreferences providesSharedPreferences(@Named("AppContext") Context context) {
        return context.getSharedPreferences("SAINO", Context.MODE_PRIVATE);
    }


    @Provides
    @LibraryScope
    SharedDataEditor providesSharedDataEditor(SharedPreferences sharedPreferences){
        return new SharedDataEditor(sharedPreferences);
    }
}
