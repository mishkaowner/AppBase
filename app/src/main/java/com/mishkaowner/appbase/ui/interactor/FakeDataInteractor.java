package com.mishkaowner.appbase.ui.interactor;

import com.mishkaowner.baselibrary.util.ISharedDataEditor;
import com.mishkaowner.baselibrary.util.TextCompat;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by Oak on 2017-06-14.
 */
public class FakeDataInteractor implements IFakeDataInteractor {
    private static final String KEY = "TEST_KEY";
    @Inject
    ISharedDataEditor sharedDataEditor;

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

    @Override
    public Observable<String> getSecureKey() {
        return Observable.create(e -> {
            try {
                Thread.sleep(1000);//Simulate network delay
                if (e != null && !e.isDisposed()) {
                    e.onNext("Hello World");
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
