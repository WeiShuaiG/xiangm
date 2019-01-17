package com.umeng.soexample.presenter;

import com.umeng.soexample.IView;
import com.umeng.soexample.callback.MyCallBack;
import com.umeng.soexample.model.ModelImpl;

/**
 * Created by W on 2018/12/29.
 */

public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private IView iView;
    public PresenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }
    @Override
    public void startRequest(String url, String phone, String password, int type) {

        model.getData(url, phone, password, type, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.success(success);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }
    public void onDetatch() {
        if (model != null) {
            model = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
