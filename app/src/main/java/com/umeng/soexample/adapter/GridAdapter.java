package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v4.app.INotificationSideChannel;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;

import java.util.List;

/**
 * Created by W on 2019/1/8 15:58.
 */

class GridAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private static final int ONE_TYPE=0;
    private static final int TEO_TYPE=1;
    private static final int THERE_TYPE=2;

    public GridAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size()==1){
            return ONE_TYPE;
        }else if (list.size() == 2){
            return TEO_TYPE;
        }else {
            return  THERE_TYPE;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder holder = null;
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        int type = getItemViewType(i);
        switch (type){
            case ONE_TYPE:
                if (view == null){
                    holder = new viewHolder();
                    view = View.inflate(context, R.layout.one_item,null);
                    holder.img = view.findViewById(R.id.img_view);
                    view.setTag(holder);
                }else {
                    holder = (viewHolder) view.getTag();
                }
                Glide.with(context).load(list.get(i)).into(holder.img);
                break;
            case TEO_TYPE:
                if (view == null){
                    holder1 = new viewHolder1();
                    view = View.inflate(context, R.layout.two_layout,null);
                    holder1.img = view.findViewById(R.id.img_view);
                    holder1.img2 = view.findViewById(R.id.img_view2);
                    view.setTag(holder1);
                }else {
                    holder1 = (viewHolder1) view.getTag();
                }
                Glide.with(context).load(list.get(i)).into(holder1.img);
                Glide.with(context).load(list.get(i)).into(holder1.img2);
                break;
            case THERE_TYPE:
                if (view == null){
                    holder2 = new viewHolder2();
                    view = View.inflate(context, R.layout.three_layout,null);
                    holder2.img = view.findViewById(R.id.img_view);
                    holder2.img2 = view.findViewById(R.id.img_view2);
                    holder2.img3 = view.findViewById(R.id.img_view3);
                    view.setTag(holder2);
                }else {
                    holder2 = (viewHolder2) view.getTag();
                }
                Glide.with(context).load(list.get(i)).into(holder2.img);
                Glide.with(context).load(list.get(i)).into(holder2.img2);
                Glide.with(context).load(list.get(i)).into(holder2.img3);
                break;
        }
        return view;
    }
    class viewHolder{
        ImageView img;
    }
    class viewHolder1{
        ImageView img;
        ImageView img2;
    }
    class viewHolder2{
        ImageView img;
        ImageView img2;
        ImageView img3;
    }
}
