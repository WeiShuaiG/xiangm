package com.umeng.soexample.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.IView;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.Dianzan;
import com.umeng.soexample.bean.Quanzi;
import com.umeng.soexample.presenter.Presenter;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by W on 2019/1/5 11:54.
 */

public class QuanziAdapter<T> extends RecyclerView.Adapter<QuanziAdapter.ViewHodel>{
    private List<Quanzi.ResultBean> list;
    private Context context;

    private int circleId;


    public QuanziAdapter(List<Quanzi.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public QuanziAdapter.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHodel hodel = null;
        View view = LayoutInflater.from(context).inflate(R.layout.quanzi_item,parent,false);
        hodel = new ViewHodel(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(@NonNull final QuanziAdapter.ViewHodel holder, final int position) {
        Glide.with(context).load(list.get(position).getHeadPic()).into(holder.quanzi_tx);
        holder.quanzi_name.setText(String.valueOf(list.get(position).getNickName()));
        holder.quanzi_time.setText(String.valueOf(list.get(position).getCreateTime()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataString = format.format(list.get(position).getCreateTime());
        holder.quanzi_time.setText(dataString);

        holder.quanzi_xq.setText(list.get(position).getContent());
        holder.quanzi_zanshu.setText(list.get(position).getGreatNum()+"");
        final CheckBox dz = holder.quanzi_dianzan;
        TextView zanshu = holder.quanzi_zanshu;

        circleId = list.get(position).getId();
        String image = list.get(position).getImage();
        String[] split = image.split("\\,");
        final List<String> list = new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
            list.add(split[i]);
        }
        if (list.size() == 1){
            holder.quanzi_image.setNumColumns(1);
        }else if (list.size()%2 ==0&&list.size()<=4){
            holder.quanzi_image.setNumColumns(2);
        }else{
            holder.quanzi_image.setNumColumns(3);
        }
        holder.quanzi_image.setAdapter(new GridAdapter(list,context));
        dz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updataLike(position);
                notifyDataSetChanged();

            }
        });


    }
    public void updataLike(int position) {
        Quanzi.ResultBean bean = list.get(position);
        int isLike = bean.getWhetherGreat();
        int greatNum = bean.getGreatNum();
        if (isLike == 1) {
            bean.setWhetherGreat(2);
            bean.setGreatNum(--greatNum);
        } else {
            bean.setWhetherGreat(1);
            bean.setGreatNum(++greatNum);
        }
        notifyItemChanged(position,bean);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHodel extends RecyclerView.ViewHolder {
        private ImageView quanzi_tx;
        private TextView quanzi_name;
        private TextView quanzi_time;
        private TextView quanzi_xq;
        private GridView quanzi_image;
        private TextView quanzi_zanshu;
        private CheckBox quanzi_dianzan;
        public ViewHodel(View itemView) {
            super(itemView);
            quanzi_tx = itemView.findViewById(R.id.quanzi_tx);
            quanzi_name = itemView.findViewById(R.id.quanzi_name);
            quanzi_time = itemView.findViewById(R.id.quanzi_time);
            quanzi_xq = itemView.findViewById(R.id.quanzi_xq);
            quanzi_image = itemView.findViewById(R.id.quanzi_image);
            quanzi_zanshu = itemView.findViewById(R.id.quanzi_zanshu);
            quanzi_dianzan = itemView.findViewById(R.id.quanzi_dz);
        }
    }
}
