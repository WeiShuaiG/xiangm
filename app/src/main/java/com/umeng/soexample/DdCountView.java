package com.umeng.soexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by W on 2019/1/17 9:06.
 */

public class DdCountView extends RelativeLayout {
    private int mnumber = 0;
    private TextView send_count_minus;
    private TextView send_count_number;
    private TextView send_count_plus;

    public DdCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.add_remove_view, this);
        initView();
    }
    private void initView() {
        send_count_minus = findViewById(R.id.delete_dt);
        send_count_number = findViewById(R.id.product_number_tv);
        send_count_plus = findViewById(R.id.add_dt);

    }
    public void setNumber(int number) {
        mnumber = number;
        send_count_number.setText(mnumber + "");
    }
}
