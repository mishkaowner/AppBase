package com.mishkaowner.appbase.ui.interactor;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Oak on 2017-06-14.
 */

public interface IFakeDataInteractor {
    Maybe<String> retrieveSavedData();

    Completable saveData(String query);
}
