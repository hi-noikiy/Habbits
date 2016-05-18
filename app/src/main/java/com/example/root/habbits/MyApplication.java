package com.example.root.habbits;

import android.app.Application;
import android.content.Context;

/**
 * 自定义Application，方便Toast中获取context
 * Created by root on 2016/5/10.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
