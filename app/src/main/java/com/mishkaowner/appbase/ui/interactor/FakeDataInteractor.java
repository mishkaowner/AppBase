package com.mishkaowner.appbase.ui.interactor;

import com.mishkaowner.appbase.data.FakeData;
import com.mishkaowner.baselibrary.util.TextCompat;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by Oak on 2017-06-14.
 */
public class FakeDataInteractor implements IFakeDataInteractor {
    private static final String KEY = "TEST_KEY";
    /*@Inject
    ISharedDataEditor sharedDataEditor;*/

    @Inject
    public FakeDataInteractor() {
    }

    @Override
    public Maybe<String> retrieveSavedData() {
        return Maybe.create(e -> {
            Realm realm = null;
            try { // I could use try-with-resources here
                realm = Realm.getDefaultInstance();
                FakeData fakeData = realm.where(FakeData.class).equalTo("id", 1).findFirst();
                if (e != null && !e.isDisposed()) {
                    if (fakeData != null && !TextCompat.isBlank(fakeData.getTitle())) {
                        e.onSuccess(fakeData.getTitle());
                    } else {
                        e.onComplete();
                    }
                }
            } catch (Exception exception) {
                if (e != null && !e.isDisposed()) {
                    e.onError(exception);
                }
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }
        });
    }

    @Override
    public Completable saveData(String query) {
        return Completable.create(e -> {
            Realm realm = null;
            try { // I could use try-with-resources here
                realm = Realm.getDefaultInstance();
                FakeData fakeData = new FakeData();
                fakeData.setId(1);
                fakeData.setAuthor(query);
                fakeData.setTitle(query);
                fakeData.setDescription(query);
                fakeData.setImageUrl(query);
                realm.executeTransaction(realm1 -> {
                    realm1.insertOrUpdate(fakeData);
                    if(e != null && !e.isDisposed()) {
                        e.onComplete();
                    }
                });
            } catch (Exception exception) {
                if (e != null && !e.isDisposed()) {
                    e.onError(exception);
                }
            } finally {
                if (realm != null) {
                    realm.close();
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
