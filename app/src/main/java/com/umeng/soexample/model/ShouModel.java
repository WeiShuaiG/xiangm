package com.umeng.soexample.model;

import com.umeng.soexample.callback.MyCallBack;

import java.util.Map;

/**
 * Created by W on 2019/1/3 20:59.
 */

public interface ShouModel {
    //put
    void getData(String url, Map<String,Object> map,Map<String,String> headmap,Class kind, MyCallBack callBack);
    //get
    void getShoushop(String url,Map<String,String> map,Map<String,String> headmap,Class kind,MyCallBack callBack);
    //post
    void post(String url, Map<String,String> map,Map<String,String> headmap,Class kind, MyCallBack callBack);
}
