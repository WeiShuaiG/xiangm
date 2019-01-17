package com.umeng.soexample.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.Contacts;
import com.umeng.soexample.IView;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.AllddAdapter;
import com.umeng.soexample.bean.AllDingdan;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class YeFragment extends Fragment implements IView{

    @BindView(R.id.recy_dingdan)
    RecyclerView recyDingdan;
    private List<AllDingdan.OrderListBean> mList = new ArrayList<>();
    private AllddAdapter adapter;
    private ShouPresenterImpl presenter;
    private SharedPreferences sp;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ye, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new AllddAdapter(mList,getActivity());
        recyDingdan.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyDingdan.setLayoutManager(layoutManager);
        sp = getActivity().getSharedPreferences("ischeck",MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);
        Map<String,String> head = new HashMap<>();
        head.put("userId",userId+"");
        head.put("sessionId",sessionId);
        Map<String,String> map = new HashMap<>();
        map.put("status",0+"");
        map.put("page",1+"");
        map.put("count",5+"");
        presenter = new ShouPresenterImpl(this);
        presenter.startShoushop(Contacts.DINGDAN_QUE,map,head,AllDingdan.class);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(Object data) {
        if (data instanceof AllDingdan){
            AllDingdan allDingdan = (AllDingdan) data;
            mList.clear();
            mList.addAll(allDingdan.getOrderList());
            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public void error(Object error) {

    }
}
