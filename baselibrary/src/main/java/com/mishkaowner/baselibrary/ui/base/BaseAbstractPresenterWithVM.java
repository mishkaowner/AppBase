package com.mishkaowner.baselibrary.ui.base;

import com.google.gson.Gson;
import com.mishkaowner.baselibrary.util.ISharedDataEditor;
import com.mishkaowner.baselibrary.util.TextCompat;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

public abstract class BaseAbstractPresenterWithVM<V extends BaseView, VM extends BaseViewModel> extends BaseAbstractPresenter<V> {
    protected static final String NO_SECURITY_KEY = "";
    protected VM vm = null;
    @Inject
    ISharedDataEditor sharedDataEditor;

    public BaseAbstractPresenterWithVM(V view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (vm == null) {
            try {
                Class<VM> vmClass = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                vm = vmClass.newInstance();
                if (vm != null) {
                    onResumeWithFreshVM(vm);
                }
            } catch (Exception e) {
                println(e.toString());
            }
        }
    }

    @Override
    public void onSave() {
        super.onSave();
        String key = getSecureKey();
        if (!TextCompat.isBlank(key)) {
            saveSecureData((Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1], key);
        } else {
            saveData((Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
        }
    }

    @Override
    public void onRestore() {
        super.onRestore();
        String key = getSecureKey();
        if (!TextCompat.isBlank(key)) {
            vm = loadSecureData((Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1], key);
        } else {
            vm = loadData((Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
        }
        if (vm != null) {
            onResumeWithRestoredVM(vm);
        }
    }

    private <T> void saveData(Class<T> t) {
        sharedDataEditor.setData(t.getName(), new Gson().toJson(vm));
    }

    private <T> void saveSecureData(Class<T> t, String password) {
        sharedDataEditor.setSecureData(t.getName(), new Gson().toJson(vm), password);
    }

    private <T> T loadData(Class<T> t) {
        String data = sharedDataEditor.getData(t.getName());
        return new Gson().fromJson(data, t);
    }

    private <T> T loadSecureData(Class<T> t, String password) {
        String data = sharedDataEditor.getSecureData(t.getName(), password);
        return new Gson().fromJson(data, t);
    }

    protected abstract void onResumeWithFreshVM(VM vm);

    protected abstract void onResumeWithRestoredVM(VM vm);

    protected String getSecureKey() {
        return NO_SECURITY_KEY;
    }
}
