package com.umeng.soexample.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by W on 2018/12/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        previewAction();
        setContentView(getContentView());
        initView();
        initData();
        setListener();
        setMoreAction();
    }

    protected void previewAction() {
    }
    protected abstract int getContentView();
    protected abstract void initView();
    protected void initData() {

    }
    protected void setListener() {

    }
    protected void setMoreAction() {

    }

}
