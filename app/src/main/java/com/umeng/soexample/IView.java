package com.umeng.soexample;

/**
 * Created by W on 2018/12/29.
 */

public interface IView<T> {
    void success(T data);
    void error(T error);
}
