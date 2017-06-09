package com.mishkaowner.baselibrary.ui.base;

import com.google.gson.Gson;
import com.mishkaowner.baselibrary.util.SharedDataEditor;
import java.lang.reflect.ParameterizedType;
import javax.inject.Inject;

/**
 * Created by jhkim on 17. 4. 28.
 */

public abstract class BaseAbstractPresenterWithVM<V extends BaseView, VM extends BaseViewModel> extends BaseAbstractPresenter<V>{
    protected VM vm = null;
    @Inject
    SharedDataEditor sharedDataEditor;

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
        if(vm == null) {
            try {
                Class<VM> vmClass = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                vm = vmClass.newInstance();
                if(vm != null) {
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
        saveData((Class<VM>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    @Override
    public void onRestore() {
        super.onRestore();
        vm = loadData((Class<VM>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
        if(vm != null) {
            onResumeWithRestoredVM(vm);
        }
    }

    private <T> void saveData(Class<T> t) {
        sharedDataEditor.setData(t.getName(), new Gson().toJson(vm));
    }

    private <T> T loadData(Class<T> t) {
        String data = sharedDataEditor.getData(t.getName());
        return new Gson().fromJson(data, t);
    }

    protected abstract void onResumeWithFreshVM(VM vm);
    protected abstract void onResumeWithRestoredVM(VM vm);
}
