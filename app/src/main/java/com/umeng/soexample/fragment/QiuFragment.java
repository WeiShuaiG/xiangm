package com.umeng.soexample.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.Contacts;
import com.umeng.soexample.IView;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.QuanziAdapter;
import com.umeng.soexample.bean.Quanzi;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class QiuFragment<T> extends Fragment implements IView<T>, XRecyclerView.LoadingListener {


    private XRecyclerView xrecyQuanzi;
    private ShouPresenterImpl presenter;
    private int page = 1;
    private int count = 5;
    private QuanziAdapter adapter;
    private HashMap<String,String> map = new HashMap<>();
    private List<Quanzi.ResultBean> mList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qiu, container, false);
        xrecyQuanzi = view.findViewById(R.id.xrecy_quanzi);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new ShouPresenterImpl(this);

        map.put("count",count+"");
        map.put("page",page+"");
        presenter.startShoushop(Contacts.QUAN_QUERY,map,null,Quanzi.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecyQuanzi.setLayoutManager(layoutManager);
        adapter = new QuanziAdapter(mList,getActivity());
        xrecyQuanzi.setAdapter(adapter);
        xrecyQuanzi.setLoadingListener(this);
        xrecyQuanzi.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        xrecyQuanzi.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);


    }
    @Override
    public void onRefresh() {
        page = 1;
        mList.clear();

        map.put("count",count+"");
        map.put("page",page+"");
        presenter.startShoushop(Contacts.QUAN_QUERY,map,null,Quanzi.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xrecyQuanzi.refreshComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        page++;

        map.put("count",count+"");
        map.put("page",page+"");
        presenter.startShoushop(Contacts.QUAN_QUERY,map,null,Quanzi.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xrecyQuanzi.refreshComplete();
            }
        },2000);

    }

    @Override
    public void success(T data) {
        Quanzi quanzi = (Quanzi) data;
        List<Quanzi.ResultBean> list = quanzi.getResult();
        mList.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void error(T error) {

    }


}
