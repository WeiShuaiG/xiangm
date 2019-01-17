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
import com.umeng.soexample.bean.ShouShop;

import java.util.List;

/**
 * Created by W on 2019/1/4 15:21.
 */

public class RexiaoAdapter extends RecyclerView.Adapter<RexiaoAdapter.ViewHolder>{
    private List<ShouShop.ResultBean.RxxpBean.CommodityListBean> list;
    private Context context;

    public RexiaoAdapter(List<ShouShop.ResultBean.RxxpBean.CommodityListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.rexiao_item,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getMasterPic()).into(holder.imglog);
        holder.txtName.setText(list.get(position).getCommodityName());
        holder.txtPrice.setText("￥"+list.get(position).getPrice()+".00");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onItemClick(view,position,list.get(position).getCommodityId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imglog;
        private TextView txtName;
        private TextView txtPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            imglog = itemView.findViewById(R.id.my_rexiao_img);
            txtName = itemView.findViewById(R.id.my_rexiao_name);
            txtPrice = itemView.findViewById(R.id.my_rexiao_price);
        }
    }
    public OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(View v,int positon ,int id);
    }

    public void setClick(OnItemClickListener listener){
        this.listener = listener;
    }
}
