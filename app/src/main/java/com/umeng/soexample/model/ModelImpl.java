package com.umeng.soexample.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.umeng.soexample.bean.Login;
import com.umeng.soexample.bean.Register;
import com.umeng.soexample.callback.MyCallBack;
import com.umeng.soexample.utils.HttpUtils;

/**
 * Created by W on 2018/12/29.
 */

public class ModelImpl implements Model {

    private MyCallBack callBack;
    private int type;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (type){
                case 1:
                    String jsonStr = (String) msg.obj;
                    Gson gson = new Gson();
                    Login login = gson.fromJson(jsonStr,Login.class);
                    callBack.setSuccess(login);
                    break;
                case 2:
                    String jsonStr1 = (String) msg.obj;
                    Gson gson1 = new Gson();
                    Register register = gson1.fromJson(jsonStr1,Register.class);
                    callBack.setSuccess(register);
                    break;

            }
        }
    };
    @Override
    public void getData(final String url, final String name, final String password, int type, final MyCallBack callBack) {

        this.callBack = callBack;
        this.type = type;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonStr = HttpUtils.post(url, name, password);
                    handler.sendMessage(handler.obtainMessage(0, jsonStr));
                } catch (Exception e) {
                    Looper.prepare();
                    callBack.setError("异常");
                    Looper.loop();
                }
            }
        }).start();



    }
}
