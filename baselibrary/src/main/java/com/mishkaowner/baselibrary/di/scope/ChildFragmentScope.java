package com.mishkaowner.baselibrary.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jhkim on 17. 6. 12.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ChildFragmentScope {
}