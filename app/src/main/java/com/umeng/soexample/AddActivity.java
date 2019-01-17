package com.umeng.soexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPickerView;
import com.umeng.soexample.bean.AddAdderss;
import com.umeng.soexample.presenter.ShouPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity<T> extends AppCompatActivity implements View.OnClickListener,IView<T>{

    @BindView(R.id.edit_sjr)
    EditText editSjr;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_dq)
    TextView editDq;
    @BindView(R.id.edit_dz)
    EditText editDz;
    @BindView(R.id.edit_yzbm)
    TextView editYzbm;
    @BindView(R.id.btn_save)
    Button btnSave;
    private String province;
    private String city;
    private String district;
    private String code;
    private SharedPreferences sp;
    private ShouPresenterImpl presenter;
    private List<AddAdderss> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        CityPickerView cityPickerView = new CityPickerView(this);
        presenter = new ShouPresenterImpl(this);
        editDq.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_dq:
                CityPickerView cityPickerView = new CityPickerView(this);
                cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                        province = citySelected[0];
                        //城市
                         city = citySelected[1];
                        //区县
                        district = citySelected[2];
                        //邮编
                        code = citySelected[3];
                        Toast.makeText(AddActivity.this, province + "-" + city + "-" + district, Toast.LENGTH_LONG).show();
                        editDq.setText(province+" "+city+" "+district);
                        editYzbm.setText(code);
                    }
                });
                cityPickerView.show();

                break;
            case R.id.btn_save:
                String name = editSjr.getText().toString();
                String phone = editPhone.getText().toString();
                String dz = editDz.getText().toString();
                String dq = editDq.getText().toString();
                String yzbn = editYzbm.getText().toString();
                String address = dq+dz;
                sp = this.getSharedPreferences("ischeck",MODE_PRIVATE);
                String sessionId = sp.getString("sessionId", null);
                int userId = sp.getInt("userId", 0);
                Map<String,String> head = new HashMap<>();
                head.put("userId",userId+"");
                head.put("sessionId",sessionId);
                Map<String,String> map = new HashMap<>();
                map.put("realName",name);
                map.put("phone",phone);
                map.put("address",address);
                map.put("zipCode",yzbn);


                presenter.post(Contacts.ADDRESS_ADD,map,head,AddAdderss.class);

                break;

        }
    }

    @Override
    public void success(T data) {
        AddAdderss addAdderss = (AddAdderss) data;
        if (addAdderss.getStatus().equals("0000")){
            Toast.makeText(this,addAdderss.getMessage(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,addAdderss.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void error(T error) {

    }
}
