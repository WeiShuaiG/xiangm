package com.umeng.soexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.soexample.bean.Register;
import com.umeng.soexample.presenter.PresenterImpl;

public class RegisterActivity<T> extends AppCompatActivity implements IView<T>, View.OnClickListener {

    private PresenterImpl presenter;
    private EditText register_phone;
    private EditText register_pwd;

    private Button bt_register;
    private String mUrl = "http://172.17.8.100/small/user/v1/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_phone = findViewById(R.id.register_phone);
        register_pwd = findViewById(R.id.register_pwd);
        bt_register = findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
        presenter = new PresenterImpl(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_register:
                String name = register_phone.getText().toString().trim();
                String pwd = register_pwd.getText().toString().trim();
                if (name.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(this,"用户名或者密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    presenter.startRequest(mUrl,name,pwd,2);
                }
                break;
        }
    }

    @Override
    public void success(Object data) {
        Register register = (Register) data;
        if(register.getStatus().equals("0000")){
            Toast.makeText(this,register.getMessage(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,register.getMessage(),Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void error(Object error) {

        Toast.makeText(this, error.toString() + "失败", Toast.LENGTH_SHORT).show();
    }


}
