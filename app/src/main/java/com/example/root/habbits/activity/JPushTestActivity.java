package com.example.root.habbits.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.root.habbits.R;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by root on 2016/5/8.
 */
public class JPushTestActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.jpush_test);
        //极光推送的关键调用代码，第一句是开启调试模式
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }




}
