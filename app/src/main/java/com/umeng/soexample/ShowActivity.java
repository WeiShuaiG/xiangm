package com.umeng.soexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.umeng.soexample.fragment.GouFragment;
import com.umeng.soexample.fragment.MyFragment;
import com.umeng.soexample.fragment.QiuFragment;
import com.umeng.soexample.fragment.ShouFragment;
import com.umeng.soexample.fragment.YeFragment;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {


    private FrameLayout shouVp;
    private RadioGroup radioGroup;
    private ShouFragment shouFragment;
    private QiuFragment qiuFragment;
    private GouFragment gouFragment;
    private YeFragment yeFragment;
    private MyFragment myFragment;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        shouVp = findViewById(R.id.shou_vp);
        radioGroup = findViewById(R.id.radio_group);
        shouFragment = new ShouFragment();
        qiuFragment = new QiuFragment();
        gouFragment = new GouFragment();
        yeFragment = new YeFragment();
        myFragment = new MyFragment();
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.shou_vp,shouFragment).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction beginTransaction = fm
                        .beginTransaction();
                switch (i){
                    case R.id.rb_shou:
                        beginTransaction.replace(R.id.shou_vp,shouFragment);
                        break;
                    case R.id.rb_qiu:
                        beginTransaction.replace(R.id.shou_vp,qiuFragment);
                        break;
                    case R.id.rb_gou:
                        beginTransaction.replace(R.id.shou_vp,gouFragment);
                        break;
                    case R.id.rb_ye:
                        beginTransaction.replace(R.id.shou_vp,yeFragment);
                        break;
                    case R.id.rb_my:
                        beginTransaction.replace(R.id.shou_vp,myFragment);
                        break;
                }
                beginTransaction.commit();
            }

        });





    }
}
