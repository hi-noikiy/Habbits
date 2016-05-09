package com.example.root.habbits.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.root.habbits.activity.JPushTestActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * 写一个receiver用来接收极光推送的通知，可以进行相应的响应
 * Created by root on 2016/5/8.
 */
public class JPushReceiver extends BroadcastReceiver {
    private final String TAG = "JPushReceiver.class";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            //使用Toast将发送的推送消息显示出来
            Toast.makeText(context,bundle.getString(JPushInterface.EXTRA_MESSAGE),Toast.LENGTH_LONG).show();
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("未测试（我自己添加的）");
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            //点击通知栏的通知，可以打开某个Activity，这里我把这个Activity的启动模式设置为SingleTop
            Intent i = new Intent(context, JPushTestActivity.class);
            //在receiver里启动一个新的Activity要给intent添加标签
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
