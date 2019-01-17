package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.bean.Address;
import com.umeng.soexample.bean.Quanzi;

import java.util.List;

/**
 * Created by W on 2019/1/11 19:09.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<Address.ResultBean> list;
    private Context context;

    public AddressAdapter(List<Address.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.address_item,parent,false);
        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.address_preson_name.setText(list.get(position).getRealName());
        holder.address_preson_phone.setText(list.get(position).getPhone());
        holder.address_preson_dizhi.setText(list.get(position).getAddress());
        holder.address_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onItemClick(view,position,list.get(position).getId());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView address_preson_name;
        private TextView address_preson_phone;
        private TextView address_preson_dizhi;
        private TextView address_check;
        private TextView address_update;
        private TextView address_delete;
        public ViewHolder(View itemView) {
            super(itemView);
            address_preson_name = itemView.findViewById(R.id.address_preson_name);
            address_preson_phone = itemView.findViewById(R.id.address_preson_phone);
            address_preson_dizhi = itemView.findViewById(R.id.address_preson_dizhi);
            address_check = itemView.findViewById(R.id.address_check);
            address_update = itemView.findViewById(R.id.address_update);
            address_delete = itemView.findViewById(R.id.address_delete);
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
