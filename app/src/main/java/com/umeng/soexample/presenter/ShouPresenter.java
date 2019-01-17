package com.umeng.soexample.presenter;

import java.util.Map;

/**
 * Created by W on 2019/1/4 10:52.
 */

public interface ShouPresenter {
    void startRequest(String url, Map<String,Object> map,Map<String,String> headmap,Class kind);
    void startShoushop(String url,Map<String,String> map,Map<String,String> headmap,Class kind);
    void post(String url, Map<String,String> map,Map<String,String> headmap,Class kind);
}
