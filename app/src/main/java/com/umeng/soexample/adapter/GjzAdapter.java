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
import com.umeng.soexample.R;
import com.umeng.soexample.bean.ShouSousuo;

import java.util.List;

/**
 * Created by W on 2019/1/9 10:45.
 */

public class GjzAdapter extends RecyclerView.Adapter<GjzAdapter.ViewHolder> {
    private List<ShouSousuo.ResultBean> list;
    private Context context;

    public GjzAdapter(List<ShouSousuo.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       ViewHolder hodel = null;
        View view = LayoutInflater.from(context).inflate(R.layout.shou_gjz_item,parent,false);
        hodel = new ViewHolder(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getMasterPic()).into(holder.img);
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("￥"+list.get(position).getPrice()+".00");
        holder.yishou.setText("已售"+list.get(position).getSaleNum()+"件");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView price;
        private TextView yishou;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.shou_gjz_img);
            name = itemView.findViewById(R.id.shou_gjz_name);
            price = itemView.findViewById(R.id.shou_gjz_price);
            yishou = itemView.findViewById(R.id.shou_gjz_yishou);
        }
    }
}
