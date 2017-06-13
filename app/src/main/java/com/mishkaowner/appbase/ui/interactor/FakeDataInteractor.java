package com.mishkaowner.appbase.ui.interactor;

import com.mishkaowner.baselibrary.util.SharedDataEditor;
import com.mishkaowner.baselibrary.util.TextCompat;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Oak on 2017-06-14.
 */
public class FakeDataInteractor implements IFakeDataInteractor {
    private static final String KEY = "TEST_KEY";
    @Inject
    SharedDataEditor sharedDataEditor;

    @Inject
    public FakeDataInteractor() {
    }

    @Override
    public Maybe<String> retrieveSavedData() {
        return Maybe.create(e -> {
            try {
                Thread.sleep(1000); //Simulate network delay
                String temp = sharedDataEditor.getData(KEY);
                if (e != null && !e.isDisposed()) {
                    if (!TextCompat.isBlank(temp)) {
                        e.onSuccess(temp);
                    } else {
                        e.onComplete();
                    }
                }
            } catch (Exception exception) {
                if (e != null && !e.isDisposed()) {
                    e.onError(exception);
                }
            }
        });
    }

    @Override
    public Completable saveData(String query) {
        return Completable.create(e -> {
            try {
                Thread.sleep(1000);//Simulate network delay
                sharedDataEditor.setData(KEY, query);
                if (e != null && !e.isDisposed()) {
                    e.onComplete();
                }
            } catch (Exception exception) {
                if (e != null && !e.isDisposed()) {
                    e.onError(exception);
                }
            }
        });
    }
}
