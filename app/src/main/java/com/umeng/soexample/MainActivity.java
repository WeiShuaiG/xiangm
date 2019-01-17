package com.umeng.soexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.bean.Login;
import com.umeng.soexample.presenter.PresenterImpl;


public class MainActivity<T> extends AppCompatActivity implements View.OnClickListener,IView<T>{
    private PresenterImpl presenter;
    //[private String mUrl = "http://mobile.bwstudent.com/small/user/v1/login";
    private String mUrl = "http://172.17.8.100/small/user/v1/login";
    private EditText loginPhone;
    private Button login_bt;
    private EditText loginPwd;
    private TextView register_jump;
    private CheckBox check;
    private ImageView imgEye;
    private boolean isHideFirst = true;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginPwd = findViewById(R.id.login_pwd);
        imgEye = findViewById(R.id.img_eye);
        login_bt = findViewById(R.id.login_bt);
        check = findViewById(R.id.check);
        register_jump = findViewById(R.id.register_jump);
        loginPhone = findViewById(R.id.login_phone);
        sp = this.getSharedPreferences("ischeck", Context.MODE_PRIVATE);
        if (sp.getBoolean("auto", false)){
            loginPhone.setText(sp.getString("uname",null));
            loginPwd.setText(sp.getString("upswd", null));
            check.setChecked(true);

        }

        imgEye.setOnClickListener(this);
        register_jump.setOnClickListener(this);
        login_bt.setOnClickListener(this);
        presenter = new PresenterImpl(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_eye:

                if (isHideFirst == true) {

                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    loginPwd.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    loginPwd.setTransformationMethod(method);
                    isHideFirst = true;

                }
                // 光标的位置
                int index = loginPwd.getText().toString().length();
                loginPwd.setSelection(index);

                break;
            case R.id.register_jump:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_bt:
                String name = loginPhone.getText().toString().trim();
                String pwd = loginPwd.getText().toString().trim();
                if (name.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(this,"用户名或者密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    presenter.startRequest(mUrl,name,pwd,1);
                }

                boolean autoLogin = check.isChecked();
                if (autoLogin) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("uname",name);
                    editor.putString("upswd", pwd);
                    editor.putBoolean("auto", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("uname",null);
                    editor.putString("upswd", null);
                    editor.putBoolean("auto", false);
                    editor.commit();
                }
                break;
        }
    }

    @Override
    public void success(Object data) {
        Login login = (Login) data;
        if (login.getStatus().equals("0000")){
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("headPic",login.getResult().getHeadPic());
            editor.putString("sessionId",login.getResult().getSessionId());
            editor.putString("nickName",login.getResult().getNickName());
            editor.putString("phone",login.getResult().getPhone());
            editor.putString("pwd",loginPwd.getText().toString());
            editor.putInt("userId",login.getResult().getUserId());
            editor.putInt("sex",login.getResult().getSex());
            editor.commit();
            Toast.makeText(this,login.getMessage(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ShowActivity.class));
            finish();
        }else{
            Toast.makeText(this,login.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void error(Object error) {
        String e = (String) error;
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();

    }
}