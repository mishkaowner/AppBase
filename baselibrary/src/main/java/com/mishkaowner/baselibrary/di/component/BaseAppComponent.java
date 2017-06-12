package com.mishkaowner.baselibrary.di.component;

import com.mishkaowner.baselibrary.di.module.BaseAppModule;
import com.mishkaowner.baselibrary.di.module.SharedDataEditorModule;
import com.mishkaowner.baselibrary.di.scope.LibraryScope;
import com.mishkaowner.baselibrary.util.SharedDataEditor;

import dagger.Component;

@LibraryScope
@Component(modules = {BaseAppModule.class, SharedDataEditorModule.class})
public interface BaseAppComponent {
    SharedDataEditor providesSharedDataEditor();
}
