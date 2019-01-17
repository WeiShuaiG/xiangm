package com.umeng.soexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.umeng.soexample.adapter.AddressAdapter;
import com.umeng.soexample.bean.Address;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressActivity<T> extends AppCompatActivity implements IView<T>, View.OnClickListener {

    @BindView(R.id.shouhuodizhi)
    TextView shouhuodizhi;
    @BindView(R.id.address_finsh)
    TextView addressFinsh;
    @BindView(R.id.add_address)
    Button addAddress;
    @BindView(R.id.recy_address)
    RecyclerView recyAddress;
    private ShouPresenterImpl presenter;
    private AddressAdapter addressAdapter;
    private List<Address.ResultBean> mList = new ArrayList<>();
    private SharedPreferences sp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        presenter = new ShouPresenterImpl(this);
        addressAdapter = new AddressAdapter(mList,this);
        recyAddress.setAdapter(addressAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyAddress.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    @Override
    protected void onStart() {
        super.onStart();

        sp = this.getSharedPreferences("ischeck", MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);

        Map<String,String> head = new HashMap<>();
        head.put("userId",userId+"");
        head.put("sessionId",sessionId);

        presenter.startShoushop(Contacts.ADDRESS_LIST,null,head,Address.class);
        addAddress.setOnClickListener(this);

    }

    @Override
    public void success(Object data) {
        Address address = (Address) data;
        mList.clear();
        mList.addAll(address.getResult());
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_address:
                Intent intent = new Intent(this,AddActivity.class);
                startActivity(intent);
                break;
        }
    }
}
