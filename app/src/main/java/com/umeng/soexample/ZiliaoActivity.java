package com.umeng.soexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.soexample.bean.Update;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZiliaoActivity<T> extends AppCompatActivity implements IView<T> {


    @BindView(R.id.my_ziliao_tx)
    ImageView myZiliaoTx;
    @BindView(R.id.my_ziliao_name)
    EditText myZiliaoName;
    @BindView(R.id.my_ziliao_pwd)
    EditText myZiliaoPwd;
    @BindView(R.id.info)
    LinearLayout info;
    private SharedPreferences sp;
    private InputMethodManager imm;
    private ShouPresenterImpl presenter;
    private String nickName_update;
    private String sessionId;
    private int userId;
    private String pwd_update;
    private String headPic;
    private String nickName;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziliao);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences("ischeck", MODE_PRIVATE);
        headPic = sp.getString("headPic", null);
        nickName = sp.getString("nickName", null);
        pwd = sp.getString("pwd", null);
        sessionId = sp.getString("sessionId", null);
        userId = sp.getInt("userId", 0);
        presenter = new ShouPresenterImpl(this);
        Glide.with(this).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myZiliaoTx);
        myZiliaoName.setText(nickName);
        myZiliaoPwd.setText(pwd);
    }

    @OnClick(R.id.info)
    public void onViewClicked() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }


    @Override
    protected void onPause() {
        super.onPause();
        nickName_update = myZiliaoName.getText().toString().trim();
        sp.edit().putString("nickName", nickName_update).commit();

        pwd_update = myZiliaoPwd.getText().toString().trim();
        sp.edit().putString("pwd", pwd_update).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HashMap<String, Object> map = new HashMap<>();
        map.put("nickName", nickName_update);
        Map<String, String> mapHeader = new HashMap<>();
        mapHeader.put("userId", userId+"");
        mapHeader.put("sessionId", sessionId);

        HashMap<String, Object> mapPwd = new HashMap<>();
        mapPwd.put("oldPwd", pwd);
        mapPwd.put("newPwd", pwd_update);
        if (!nickName_update.equals(nickName)) {
            Log.e("修改昵称", nickName_update);
            presenter.startRequest(Contacts.USER_UPDATENAME, map, mapHeader, Update.class);
        }
        if (!pwd_update.equals(pwd)) {
            Log.e("修改密码", pwd_update);
            presenter.startRequest(Contacts.USER_UPDATPWD, mapPwd, mapHeader, Update.class);
        }
    }


    @Override
    public void success(T data) {
        if (data instanceof Update) {
            Toast.makeText(this, ((Update) data).getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void error(T error) {

    }


}
