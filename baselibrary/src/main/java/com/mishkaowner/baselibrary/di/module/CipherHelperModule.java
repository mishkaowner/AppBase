package com.mishkaowner.baselibrary.di.module;

import com.mishkaowner.baselibrary.di.scope.LibraryScope;
import com.mishkaowner.baselibrary.util.CipherUtil;
import com.mishkaowner.baselibrary.util.ICipherUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jhkim on 17. 6. 16.
 */
@Module
public class CipherHelperModule {
    @Provides
    @LibraryScope
    ICipherUtil providesCipherUtil(CipherUtil cipherUtil) {
        return cipherUtil;
    }
}
