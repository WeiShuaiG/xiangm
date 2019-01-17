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
 * Created by W on 2019/1/4 19:04.
 */

public class ShenghuoAdapter extends RecyclerView.Adapter<ShenghuoAdapter.ViewHodel> {
    private List<ShouShop.ResultBean.PzshBean.CommodityListBeanX> list;
    private Context context;

    public ShenghuoAdapter(List<ShouShop.ResultBean.PzshBean.CommodityListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHodel hodel = null;
        View view = LayoutInflater.from(context).inflate(R.layout.shenghuo_item,parent,false);
        hodel = new ViewHodel(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, final int position) {
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

    public class ViewHodel extends RecyclerView.ViewHolder {
        private ImageView imglog;
        private TextView txtName;
        private TextView txtPrice;
        public ViewHodel(View itemView) {
            super(itemView);
            imglog = itemView.findViewById(R.id.my_shenghuo_img);
            txtName = itemView.findViewById(R.id.my_shenghuo_name);
            txtPrice = itemView.findViewById(R.id.my_shenghuo_price);
        }
    }
    public RexiaoAdapter.OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(View v,int positon ,int id);
    }

    public void setClick(RexiaoAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
