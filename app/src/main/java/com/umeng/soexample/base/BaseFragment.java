package com.umeng.soexample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by W on 2018/12/26.
 */

public abstract class BaseFragment extends Fragment {
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(getContentView(), container, false);
        initView(v);
        return v;
    }
    protected abstract int getContentView();
    protected abstract void initView(View v);
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mContext == null) {
            mContext = getActivity();
        }
        initData();
        setListener();
        setMoreAction();
    }
    protected void initData() {

    }
    protected void setListener() {

    }
    protected void setMoreAction() {

    }
}
