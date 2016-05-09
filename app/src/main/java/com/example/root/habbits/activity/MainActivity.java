package com.example.root.habbits.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.habbits.R;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView activityList;
    private List<String> activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏，要在setContentView之前使用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        activityList = (ListView) findViewById(R.id.activityList);
        activityList.setOnItemClickListener(this);
        activityName = new ArrayList<String>();


        activityName.add("极光推送测试");
        activityName.add("ActionBar测试");
        activityList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,activityName));


    }

    /**
     * 处理点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        //根据名字不同，启动不同的Activity
        switch (activityName.get(position)){
            case "极光推送测试":
                //第一次写的时候忘了注册Activity，结果显示找不到该Activity
                intent = new Intent(this,JPushTestActivity.class);
                startActivity(intent);
                break;
            case "ActionBar测试":
                intent = new Intent(this,ActionBarTestActivity.class);
                startActivity(intent);
                break;
        }

    }

    //老是提醒我统计信息没添加，添加上
    @Override
    protected void onPause() {
        JPushInterface.onPause(this);
        super.onPause();
    }
    @Override
    protected void onResume() {
        JPushInterface.onResume(this);
        super.onResume();
    }
}
