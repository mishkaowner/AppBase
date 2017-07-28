package com.mishkaowner.baselibrary.di.module;

import com.mishkaowner.baselibrary.di.scope.LibraryScope;
import com.mishkaowner.baselibrary.util.CipherUtil;
import com.mishkaowner.baselibrary.util.ICipherUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class CipherHelperModule {
    @Provides
    @LibraryScope
    ICipherUtil providesCipherUtil(CipherUtil cipherUtil) {
        return cipherUtil;
    }
}
