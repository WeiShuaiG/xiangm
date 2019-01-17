package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.bean.AllDingdan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by W on 2019/1/16 10:18.
 */

public class AllddAdapter extends RecyclerView.Adapter<AllddAdapter.ViewHolder> {
    private List<AllDingdan.OrderListBean> list;
    private Context context;

    public AllddAdapter(List<AllDingdan.OrderListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.dingdan_item,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.orderPendingPaymentNumber.setText(list.get(position).getOrderId());
        holder.orderPendingPaymentSize.setText(list.get(position).getDetailList().size() + "");
        holder.orderPendingPaymentPrice.setText(list.get(position).getPayAmount() + "");
        holder.orderPendingPaymentRecycle.setLayoutManager(new LinearLayoutManager(context));
        DingpageAdapter adapter = new DingpageAdapter((ArrayList<AllDingdan.OrderListBean.DetailListBean>) list.get(position).getDetailList(),context);
        holder.orderPendingPaymentRecycle.setAdapter(adapter);
        holder.orderPendingPaymentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    itemClick.cancel(v, position);
                }
            }
        });
        holder.orderPendingPaymentPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    itemClick.pay(v, position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_PendingPayment_ddh)
        TextView orderPendingPaymentDdh;
        @BindView(R.id.order_PendingPayment_number)
        TextView orderPendingPaymentNumber;
        @BindView(R.id.order_PendingPayment_time)
        TextView orderPendingPaymentTime;
        @BindView(R.id.order_PendingPayment_recycle)
        RecyclerView orderPendingPaymentRecycle;
        @BindView(R.id.order_PendingPayment_size)
        TextView orderPendingPaymentSize;
        @BindView(R.id.order_PendingPayment_price)
        TextView orderPendingPaymentPrice;
        @BindView(R.id.order_PendingPayment_lin)
        LinearLayout orderPendingPaymentLin;
        @BindView(R.id.order_PendingPayment_cancel)
        Button orderPendingPaymentCancel;
        @BindView(R.id.order_PendingPayment_payment)
        Button orderPendingPaymentPayment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface itemClick {
        void pay(View v, int position);

        void cancel(View v, int position);

        void queren(View v, int position);
    }

    private itemClick itemClick;

    public void setItemClick(itemClick itemClick) {
        this.itemClick = itemClick;
    }
}
