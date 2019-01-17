package com.umeng.soexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.adapter.SubmitAdaptr;
import com.umeng.soexample.bean.OrderBean;
import com.umeng.soexample.bean.Submit;
import com.umeng.soexample.presenter.PresenterImpl;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TijiaoActivity<T> extends AppCompatActivity implements IView<T>, View.OnClickListener {
    private RecyclerView mCreatePostRecycle;
    private TextView mCreatePayment;
    private TextView mCreateSubmission;
    private SubmitAdaptr submitAdaptr;
    private ArrayList<Submit> submit;
    private ShouPresenterImpl presenter;
    private int userId;
    private String sessionId;
    private int allGoodsPrice;
    private TextView mTextAddress;
    private RecyclerView mRecyAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijiao);
        initView();

    }
    private void initView() {
        presenter = new ShouPresenterImpl(this);
        Intent intent = getIntent();
        submit = intent.getParcelableArrayListExtra("submit");

        Log.e("submit", submit.toString());


        mCreatePostRecycle = (RecyclerView) findViewById(R.id.create_post_recycle);
        mCreatePayment = (TextView) findViewById(R.id.create_payment);
        mCreateSubmission = (TextView) findViewById(R.id.create_Submission);
        mCreateSubmission.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mCreatePostRecycle.setLayoutManager(manager);
        submitAdaptr = new SubmitAdaptr(submit,this);
        mCreatePostRecycle.setAdapter(submitAdaptr);
        submitAdaptr.notifyDataSetChanged();
        flushBottomLayout();
        mCreatePostRecycle.addItemDecoration(new SpaceItemDecoration(20));

    }
    private void flushBottomLayout() {
        int allGoodsNumber = submitAdaptr.getCount();
        allGoodsPrice = submitAdaptr.getSumPrice();
        mCreatePayment.setText("共" + allGoodsNumber + "件商品需付款" + allGoodsPrice + "元");
    }

    @Override
    public void success(Object data) {
        if (data instanceof OrderBean) {
            Toast.makeText(this, ((OrderBean) data).getMessage(), Toast.LENGTH_SHORT).show();
            if(((OrderBean) data).getStatus().equals("0000"))
            {
                finish();
            }
        }

    }

    @Override
    public void error(Object error) {

        if (error instanceof OrderBean) {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_Submission:
                SharedPreferences shopping = getSharedPreferences("ischeck", Context.MODE_PRIVATE);
                userId = shopping.getInt("userId", 0);
                sessionId = shopping.getString("sessionId", null);
                Map<String, String> body = new HashMap<>();
                Map<String, String> head = new HashMap<>();
                head.put("userId", userId+"");
                head.put("sessionId", sessionId);
                body.put("orderInfo", submit.toString());
                body.put("totalPrice", allGoodsPrice+"");
                body.put("addressId", 650+"");

                presenter.post(Contacts.DINGDAN, body, head, OrderBean.class);
                break;
        }

    }
    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mSpace;
            }

        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
