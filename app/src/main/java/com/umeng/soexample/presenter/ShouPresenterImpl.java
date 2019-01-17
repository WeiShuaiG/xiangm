package com.umeng.soexample.presenter;

import android.view.View;

import com.umeng.soexample.IView;
import com.umeng.soexample.callback.MyCallBack;
import com.umeng.soexample.model.ShouModelImpl;

import java.util.Map;


/**
 * Created by W on 2019/1/4 10:54.
 */

public class ShouPresenterImpl implements ShouPresenter {
    private ShouModelImpl model;
    private IView iView;
    public ShouPresenterImpl(IView iView){
        this.iView = iView;
        model = new ShouModelImpl();
    }

    @Override
    public void startRequest(String url, Map<String, Object> map, Map<String, String> headmap, Class kind) {
        model.getData(url, map, headmap, kind, new MyCallBack() {
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


    @Override
    public void startShoushop(String url,Map<String, String> map,Map<String, String> headmap, Class kind) {
        model.getShoushop(url, map,headmap,kind, new MyCallBack() {
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

    @Override
    public void post(String url, Map<String, String> map, Map<String, String> headmap, Class kind) {
        model.post(url, map, headmap, kind, new MyCallBack() {
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
}
