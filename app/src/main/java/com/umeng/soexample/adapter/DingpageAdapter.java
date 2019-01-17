package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.CountView;
import com.umeng.soexample.DdCountView;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.AllDingdan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by W on 2019/1/16 14:32.
 */

public class DingpageAdapter extends RecyclerView.Adapter<DingpageAdapter.ViewHolder> {

    private List<AllDingdan.OrderListBean.DetailListBean> list;
    private Context context;

    public DingpageAdapter(List<AllDingdan.OrderListBean.DetailListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view  = LayoutInflater.from(context).inflate(R.layout.ding_page_item,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText(list.get(position).getCommodityPrice()+"");
        holder.item_count.setNumber(list.get(position).getCommodityCount());
        holder.item_count.setFocusable(false);
        String image = list.get(position).getCommodityPic();
        String[] split = image.split("\\,");
        Glide.with(context).load(split[0]).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView price;
        DdCountView item_count;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.page_item_img);
            name = itemView.findViewById(R.id.page_item_name);
            price = itemView.findViewById(R.id.page_item_price);
            item_count = itemView.findViewById(R.id.page_item_countt);

        }
    }
}
