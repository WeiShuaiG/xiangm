package com.umeng.soexample.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.soexample.AddressActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.ZiliaoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.my_home_address)
    TextView myHomeAddress;
    Unbinder unbinder;
    private ImageView my_home_tou;
    private TextView my_xiaojiejei;
    private TextView my_home_ziliao;
    private SharedPreferences sp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        my_home_tou = view.findViewById(R.id.my_home_tou);
        my_xiaojiejei = view.findViewById(R.id.my_xiaojiejie);
        my_home_ziliao = view.findViewById(R.id.my_home_ziliao);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sp = getActivity().getSharedPreferences("ischeck", Context.MODE_PRIVATE);
        String nickName = sp.getString("nickName", null);
        String headPic = sp.getString("headPic", null);
        my_xiaojiejei.setText(nickName);
        Glide.with(getActivity()).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(my_home_tou);
        my_home_ziliao.setOnClickListener(this);
        myHomeAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_home_ziliao:
                Intent intent = new Intent(getActivity(), ZiliaoActivity.class);
                startActivity(intent);
                break;
            case R.id.my_home_address:
                Intent intent1 = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
