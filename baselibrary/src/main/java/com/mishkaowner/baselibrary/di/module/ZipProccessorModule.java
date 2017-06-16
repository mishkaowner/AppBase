package com.mishkaowner.baselibrary.di.module;

import com.mishkaowner.baselibrary.di.scope.LibraryScope;
import com.mishkaowner.baselibrary.util.ZipProccessor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jhkim on 17. 6. 14.
 */
@Module
public class ZipProccessorModule {
    @Provides
    @LibraryScope
    ZipProccessor providesZipProccessor(){
        return new ZipProccessor();
    }
}
