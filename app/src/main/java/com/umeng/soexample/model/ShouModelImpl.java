package com.umeng.soexample.model;

import com.google.gson.Gson;
import com.umeng.soexample.bean.ShouShop;
import com.umeng.soexample.callback.MyCallBack;
import com.umeng.soexample.network.RetrofitUtils;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by W on 2019/1/3 20:59.
 */

public class ShouModelImpl implements ShouModel {

    @Override
    public void getData(String url, Map<String, Object> map, Map<String, String> headmap, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().put(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object loginBean = gson.fromJson(jsonStr,kind);
                callBack.setSuccess(loginBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void getShoushop(String url,Map<String,String> map, Map<String, String> headmap,final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().get(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object s = gson.fromJson(jsonStr, kind);
                callBack.setSuccess(s);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void post(String url, Map<String, String> map, Map<String, String> headmap, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().post(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object s =gson.fromJson(jsonStr,kind);
                callBack.setSuccess(s);

            }

            @Override
            public void onError(String error) {

            }
        });
    }


}
