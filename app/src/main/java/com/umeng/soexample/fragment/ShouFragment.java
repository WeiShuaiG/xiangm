package com.umeng.soexample.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.soexample.Contacts;
import com.umeng.soexample.GjzFragment;
import com.umeng.soexample.IView;
import com.umeng.soexample.MainActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.XiangqingActivity;
import com.umeng.soexample.adapter.RexiaoAdapter;
import com.umeng.soexample.adapter.ShenghuoAdapter;
import com.umeng.soexample.adapter.ShishangAdapter;
import com.umeng.soexample.adapter.ShouPageAdapter;
import com.umeng.soexample.bean.ShouShop;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShouFragment<T> extends Fragment implements IView<T>, View.OnClickListener {


    @BindView(R.id.shou_img_fen)
    ImageView shouImgFen;
    @BindView(R.id.recy_shouye_rexiao)
    RecyclerView recyShouyeRexiao;
    @BindView(R.id.recy_shouye_shishang)
    RecyclerView recyShouyeShishang;
    @BindView(R.id.recy_shouye_shenghuo)
    RecyclerView recyShouyeShenghuo;
    Unbinder unbinder;
    private ViewPager vp;
    private ImageView img_btn;
    private List<Integer> mList = new ArrayList<>();
    private RexiaoAdapter rexiaoAdapter;
    private ShenghuoAdapter shenghuoAdapter;
    private ShishangAdapter shishangAdapter;
    private ShouPresenterImpl presenter;
    private GjzFragment gjzFragment;
    private YeFragment yeFragment = new YeFragment();
    private Bundle bundle;
    private List<ShouShop.ResultBean.RxxpBean.CommodityListBean> rexiao = new ArrayList<>();
    private List<ShouShop.ResultBean.MlssBean.CommodityListBeanXX> shishang = new ArrayList<>();
    private List<ShouShop.ResultBean.PzshBean.CommodityListBeanX> shenghuo = new ArrayList<>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int i = vp.getCurrentItem();
                i++;
                vp.setCurrentItem(i);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }


    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shou, container, false);
        vp = view.findViewById(R.id.shou_img_vp);
        img_btn = view.findViewById(R.id.shou_img_sou);
        unbinder = ButterKnife.bind(this, view);

        mList.add(R.drawable.a);
        mList.add(R.drawable.b);
        mList.add(R.drawable.c);
        mList.add(R.drawable.d1);

        ShouPageAdapter homePagerAdapter = new ShouPageAdapter(mList, getActivity());
        vp.setAdapter(homePagerAdapter);
        vp.setCurrentItem(mList.size() * 1000);
        gjzFragment = new GjzFragment();



        rexiaoAdapter = new RexiaoAdapter(rexiao,getActivity());
        shishangAdapter = new ShishangAdapter(shishang,getActivity());
        shenghuoAdapter = new ShenghuoAdapter(shenghuo,getActivity());

        recyShouyeRexiao.setAdapter(rexiaoAdapter);
        recyShouyeShenghuo.setAdapter(shenghuoAdapter);
        recyShouyeShishang.setAdapter(shishangAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyShouyeShenghuo.setLayoutManager(staggeredGridLayoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyShouyeShishang.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyShouyeRexiao.setLayoutManager(gridLayoutManager);

        presenter = new ShouPresenterImpl(this);

        presenter.startShoushop(Contacts.SHOU_SHOP,null,null,ShouShop.class);
        img_btn.setOnClickListener(this);
        bundle = new Bundle();
        rexiaoAdapter.setClick(new RexiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int positon, int id) {

                ShouShop.ResultBean.RxxpBean.CommodityListBean bean = new ShouShop.ResultBean.RxxpBean.CommodityListBean();
                bean.setSaleNum(rexiao.get(positon).getSaleNum());
                bean.setPrice(rexiao.get(positon).getPrice());
                bean.setCommodityId(rexiao.get(positon).getCommodityId());
                bean.setCommodityName(rexiao.get(positon).getCommodityName());
                bean.setMasterPic(rexiao.get(positon).getMasterPic());
                bundle.putParcelable("bean",bean);
                Intent intent = new Intent(getActivity(), XiangqingActivity.class);
                intent.putExtra("rxxpBean",bundle);
                startActivity(intent);
            }
        });
        shishangAdapter.setClick(new RexiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int positon, int id) {
                ShouShop.ResultBean.MlssBean.CommodityListBeanXX bean = new ShouShop.ResultBean.MlssBean.CommodityListBeanXX();
                bean.setSaleNum(shishang.get(positon).getSaleNum());
                bean.setPrice(shishang.get(positon).getPrice());
                bean.setCommodityId(shishang.get(positon).getCommodityId());
                bean.setCommodityName(shishang.get(positon).getCommodityName());
                bean.setMasterPic(shishang.get(positon).getMasterPic());
                bundle.putParcelable("bean",bean);
                Intent intent = new Intent(getActivity(), XiangqingActivity.class);
                intent.putExtra("rxxpBean",bundle);
                startActivity(intent);
            }
        });
        shenghuoAdapter.setClick(new RexiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int positon, int id) {
                ShouShop.ResultBean.PzshBean.CommodityListBeanX bean = new ShouShop.ResultBean.PzshBean.CommodityListBeanX();
                bean.setSaleNum(shenghuo.get(positon).getSaleNum());
                bean.setPrice(shenghuo.get(positon).getPrice());
                bean.setCommodityId(shenghuo.get(positon).getCommodityId());
                bean.setCommodityName(shenghuo.get(positon).getCommodityName());
                bean.setMasterPic(shenghuo.get(positon).getMasterPic());
                bundle.putParcelable("bean",bean);
                Intent intent = new Intent(getActivity(), XiangqingActivity.class);
                intent.putExtra("rxxpBean",bundle);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        // 5.设置自动轮播
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //7.页面消失的时候,清空消息
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(T data) {
        if (data instanceof ShouShop){
            ShouShop shouShop = (ShouShop) data;

            rexiao.clear();
            shishang.clear();
            shenghuo.clear();
            rexiao.addAll(shouShop.getResult().getRxxp().get(0).getCommodityList());
            shishang.addAll(shouShop.getResult().getMlss().get(0).getCommodityList());
            shenghuo.addAll(shouShop.getResult().getPzsh().get(0).getCommodityList());

            rexiaoAdapter.notifyDataSetChanged();
            shishangAdapter.notifyDataSetChanged();
            shenghuoAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void error(T error) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shou_img_sou:
                FragmentManager manager = getFragmentManager();
                FragmentTransaction beginTransaction = manager
                        .beginTransaction();
                beginTransaction.replace(R.id.shou_vp,gjzFragment).addToBackStack(null).commit();
                break;
        }
    }
}
