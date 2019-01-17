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
import com.umeng.soexample.R;
import com.umeng.soexample.bean.Submit;

import java.util.List;

/**
 * Created by W on 2019/1/15 20:42.
 */

public class SubmitAdaptr extends RecyclerView.Adapter<SubmitAdaptr.ViewHolder> {
    private List<Submit> list;
    private Context context;

    public SubmitAdaptr(List<Submit> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.tijiao_item,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getPic()).into(holder.img);
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("￥"+list.get(position).getPrice()+".00");
        holder.item_count.setNumber(list.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public int getCount() {
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num += list.get(i).getCount();
        }
        return num;
    }

    //获得选中的商品的价格
    public int getSumPrice() {
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num += list.get(i).getCount() * list.get(i).getPrice();
        }
        return num;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView price;
        CountView item_count;


        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tj_item_img);
            name = itemView.findViewById(R.id.tj_item_name);
            price = itemView.findViewById(R.id.tj_item_price);
            item_count = itemView.findViewById(R.id.item_countt);
        }
    }
    public interface OnItemClick {

        void onJiaClick(View view, int i);

        void onJianClick(View view, int i);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
