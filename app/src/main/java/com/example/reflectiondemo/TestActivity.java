package com.example.reflectiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annotation.FindId;
import com.example.annotationapi.AnnotationHelp;
import com.example.annotation.OnClick;

import com.example.reflectiondemo.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@FindId(R.layout.activity_test)
public class TestActivity extends AppCompatActivity{

    private static final String TAG = "TestActivity";

    @FindId(R.id.tv_certain)
    TextView tv_certain;

    List<WeakReference<String>> weekList=new ArrayList<>();
    List<String> normalList=new ArrayList<>();

    List<String> linkedList=new LinkedList<>();

    Map<String,String> hashmap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test);
        AnnotationHelp.inject(this);
        //tv_certain=findViewById(R.id.tv_certain);
        //tv_certain.setOnClickListener(this);
        Log.e(TAG,"weakSize:"+weekList.size()+" normalSize:"+normalList.size());
        for (int i = 0; i < 10; i++) {
            addData();
        }
        normalList.clear();
        Log.e("===size===","==="+normalList.size());

    }

    @OnClick({R.id.tv_certain})
    public void myClick(View v) {
        switch (v.getId()){
            case R.id.tv_certain:
                addData();
                Toast.makeText(this,"执行了",Toast.LENGTH_LONG).show();
                Log.e(TAG,"weakSize:"+weekList.size()+" normalSize:"+normalList.size());
                break;
            default:
                break;
        }
    }

    private void addData() {
        WeakReference<String> string = new WeakReference<>("测试");
        weekList.add(string);
        normalList.add("测试");
    }
}
