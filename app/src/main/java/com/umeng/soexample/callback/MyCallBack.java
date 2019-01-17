package com.umeng.soexample.callback;

/**
 * Created by W on 2018/12/26.
 */

public interface MyCallBack<T> {
    void setSuccess(T success);
    void setError(T error);
}
