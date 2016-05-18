package com.example.root.habbits.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.habbits.R;

import java.util.List;

/**
 * Created by root on 2016/5/15.
 */
public class GetContactActivity extends Activity{
    private Button getContacts;
    private TextView packageName;
    private PackageManager pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_contact_activity);

        getContacts = (Button) findViewById(R.id.get_contact);
        //获取联系人，跳转到联系人界面，发送一个设置了Action的Intent
        getContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }
        });

        packageName = (TextView) findViewById(R.id.package_name);
        //获取已经安装在该手机上的应用程序的包的信息
        pm = getPackageManager();
        //返回每个安装包信息的List
        List<PackageInfo> list = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        StringBuilder sb = new StringBuilder();
        for(PackageInfo packageInfo:list){
            sb.append("包名：" + packageInfo.packageName + "\n");
            //获取应用程序的名称，也就是Label
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            sb.append("应用名称：" + applicationInfo.loadLabel(pm) + "\n");
        }
        packageName.setText(sb.toString());

    }
}
