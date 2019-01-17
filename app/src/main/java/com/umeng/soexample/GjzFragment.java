package com.umeng.soexample;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.adapter.GjzAdapter;
import com.umeng.soexample.bean.Quanzi;
import com.umeng.soexample.bean.ShouSousuo;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class GjzFragment<T> extends Fragment implements IView<T>, XRecyclerView.LoadingListener {


    @BindView(R.id.shou_gjc_sou)
    EditText shouGjcSou;
    @BindView(R.id.shou_gjz_recy)
    XRecyclerView shouGjzRecy;
    Unbinder unbinder;
    @BindView(R.id.gjz_sou)
    TextView gjzSou;
    private GjzAdapter adapter;
    private List<ShouSousuo.ResultBean> mList = new ArrayList<>();
    private ShouPresenterImpl presenter;
    private int page = 1;
    private int count = 6;
    private HashMap<String, String> map = new HashMap<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gjz, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        shouGjzRecy.setLayoutManager(staggeredGridLayoutManager);
        adapter = new GjzAdapter(mList, getActivity());
        shouGjzRecy.setAdapter(adapter);
        shouGjzRecy.setLoadingListener(this);
        shouGjzRecy.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        shouGjzRecy.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(T data) {
        ShouSousuo sousuo = (ShouSousuo) data;
        List<ShouSousuo.ResultBean> list = sousuo.getResult();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error(T error) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        mList.clear();
        String keyword = shouGjcSou.getText().toString().trim();
        map.put("keyword", keyword + "");
        map.put("count", count + "");
        map.put("page", page + "");
        presenter.startShoushop(Contacts.SHOU_SHOP_SOUSUO, map, null,ShouSousuo.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shouGjzRecy.refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        page++;
        String keyword = shouGjcSou.getText().toString().trim();
        map.put("keyword", keyword + "");
        map.put("count", count + "");
        map.put("page", page + "");
        presenter.startShoushop(Contacts.SHOU_SHOP_SOUSUO, map,null, ShouSousuo.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shouGjzRecy.refreshComplete();
                if (page == mList.size()-1){
                    Toast.makeText(getActivity(),"没有更多数据了",Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);
    }

    @OnClick(R.id.gjz_sou)
    public void onViewClicked() {
        String keyword = shouGjcSou.getText().toString().trim();
        map.put("count", count + "");
        map.put("page", page + "");
        map.put("keyword", keyword + "");
        presenter = new ShouPresenterImpl(this);
        presenter.startShoushop(Contacts.SHOU_SHOP_SOUSUO, map,null, ShouSousuo.class);

    }
}
