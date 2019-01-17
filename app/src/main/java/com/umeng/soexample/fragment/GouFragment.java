package com.umeng.soexample.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.umeng.soexample.Contacts;
import com.umeng.soexample.IView;
import com.umeng.soexample.R;
import com.umeng.soexample.TijiaoActivity;
import com.umeng.soexample.adapter.GwcAdapter;
import com.umeng.soexample.bean.Querygwc;
import com.umeng.soexample.bean.Submit;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class GouFragment<T> extends Fragment implements IView<T>, View.OnClickListener {


    @BindView(R.id.query_gwc)
    RecyclerView queryGwc;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.gou_heji)
    TextView gouHeji;
    @BindView(R.id.js)
    TextView js;
    Unbinder unbinder;
    private ShouPresenterImpl presenter;
    private GwcAdapter adapter;
    private SharedPreferences sp;
    private List<Querygwc.ResultBean> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gou, container, false);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("ischeck", Context.MODE_PRIVATE);
        presenter = new ShouPresenterImpl(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        queryGwc.setLayoutManager(layoutManager);
        Map<String,String> headmap = new HashMap<>();
        int userId = sp.getInt("userId",0);
        String sessionId = sp.getString("sessionId",null);
        headmap.put("userId",userId+"");
        headmap.put("sessionId",sessionId);
        adapter = new GwcAdapter(mList,getActivity());
        queryGwc.setAdapter(adapter);
        Map<String,Object> map = new HashMap<>();
        presenter.startShoushop(Contacts.GWC_QUERY,null,headmap,Querygwc.class);

        js.setOnClickListener(this);
        adapter.setOnItemClick(new GwcAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                boolean status = adapter.thisCheckStatus(position);
                adapter.setCheckStatus(position, !status);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onDelete(View view, int position) {
                mList.remove(position);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onNumber(int position, int number) {
                adapter.setShopCount(position, number);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = adapter.allCheckStatus();
                adapter.setAllCheckStatus(!status);
                check.setChecked(!status);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }
        });

        return view;
    }
    private void FlushFooter() {
        boolean status = adapter.allCheckStatus();
        check.setChecked(status);
        double allPrice = adapter.getAllPrice();
        gouHeji.setText("￥" + allPrice);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(T data) {
        Querygwc querygwc = (Querygwc) data;
        mList.clear();
        mList.addAll(querygwc.getResult());
        adapter.notifyDataSetChanged();


    }

    @Override
    public void error(T error) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.js:
                Intent intent = new Intent(getActivity(), TijiaoActivity.class);
                List<Submit> list = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChildCheck() == true) {
                        Submit bean = new Submit(mList.get(i).getCommodityId(), mList.get(i).getCount(), mList.get(i)
                                .getCommodityName(), mList.get(i).getCount(), mList.get(i).getPic(), mList.get(i).getPrice());
                        list.add(bean);
                    }
                }
                intent.putParcelableArrayListExtra("submit", (ArrayList<? extends Parcelable>) list);
                startActivity(intent);
                break;
        }
    }
}
