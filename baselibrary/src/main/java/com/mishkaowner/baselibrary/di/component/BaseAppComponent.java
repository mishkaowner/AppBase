package com.mishkaowner.baselibrary.di.component;

import com.mishkaowner.baselibrary.di.module.BaseAppModule;
import com.mishkaowner.baselibrary.di.module.CipherHelperModule;
import com.mishkaowner.baselibrary.di.module.SharedDataEditorModule;
import com.mishkaowner.baselibrary.di.module.ZipProccessorModule;
import com.mishkaowner.baselibrary.di.scope.LibraryScope;
import com.mishkaowner.baselibrary.util.ICipherUtil;
import com.mishkaowner.baselibrary.util.ISharedDataEditor;

import dagger.Component;

@LibraryScope
@Component(modules = {BaseAppModule.class, SharedDataEditorModule.class, CipherHelperModule.class, ZipProccessorModule.class})
public interface BaseAppComponent {
    ISharedDataEditor providesSharedDataEditor();
    ICipherUtil providesCipherUtil();
}
