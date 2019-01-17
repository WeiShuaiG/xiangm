package com.umeng.soexample.model;

import com.umeng.soexample.callback.MyCallBack;

/**
 * Created by W on 2018/12/29.
 */

public interface Model {
    void getData(String url, String name, String password,int type, MyCallBack callBack);
}
