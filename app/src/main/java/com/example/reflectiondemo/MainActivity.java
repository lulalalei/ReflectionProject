package com.example.reflectiondemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reflectiondemo.annotations.BindId;
import com.example.reflectiondemo.annotations.BindIdApi;
import com.example.reflectiondemo.annotations.OnClick;
import com.example.reflectiondemo.bean.User;
import com.example.reflectiondemo.utils.LogUtil;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

@BindId(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindId(R.id.tv_test)
    TextView tv_test;
    @BindId(R.id.tv_certain)
    TextView tv_certain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindIdApi.bindId(this);
        BindIdApi.bindOnClick(this);
//        setContentView(R.layout.activity_main);
//        tv_certain=findViewById(R.id.tv_certain);
//        tv_certain.setOnClickListener(this);
//        tv_test=findViewById(R.id.tv_test);
//        tv_test.setOnClickListener(this);

        LogUtil.e("==hash=="," &: "+(12&15)+" ,除:"+(12%16));
    }

    @OnClick({R.id.tv_test,R.id.tv_certain})
    public void click(View v) {
        switch (v.getId()){
            case R.id.tv_certain:
                createUser();
                break;
            case R.id.tv_test:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public User createUser(){
        //获取user类的Class对象,基本数据类型同样可以使用
        Class<User> userClass = User.class;
        try {
            Constructor<User> constructor = userClass.getConstructor(String.class, int.class);
            User user = constructor.newInstance("反射", 22);
            LogUtil.d("createUser",user.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
