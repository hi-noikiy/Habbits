package com.example.root.habbits.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.root.habbits.R;
import com.example.root.habbits.adapter.ContentAdapter;
import com.example.root.habbits.model.ContentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016/5/10.
 */
//如果是继承自AppCompatActivity的话，requestWindowFeature(Window.FEATURE_NO_TITLE);是无法消除ActionBar的
public class MyCallActivity extends Activity {
    private static final String TAG = "MyCallActivity";
    private ListView listView;
    private List<ContentBean> list;
    private ContentBean bean;
    @Override
    //之前用的AppCompatActivity的OnCreate方法，不显示ListView，被我换成了Activity的OnCreate就可以了，原因不明
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.call_activity);

        listView = (ListView) findViewById(R.id.call_list);
        list = new ArrayList<>();

        //每次使用之前都需要new一遍，否则全部显示最后添加的那个
        bean = new ContentBean();
        bean.setName("刘大美女");
        bean.setNumber("15071329351");
        list.add(bean);

        bean = new ContentBean();
        bean.setName("周狗子");
        bean.setNumber("13407195518");
        list.add(bean);

        bean = new ContentBean();
        bean.setName("葱葱姐");
        bean.setNumber("15165582769");
        list.add(bean);

        ContentAdapter adapter = new ContentAdapter(this,list);
        listView.setAdapter(adapter);
    }

}
