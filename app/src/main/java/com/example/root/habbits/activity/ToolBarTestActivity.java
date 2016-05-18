package com.example.root.habbits.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.root.habbits.R;

/**
 * Created by root on 2016/5/14.
 */
public class ToolBarTestActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("subTitle"); //4.4显示不出来
        toolbar.setLogo(R.mipmap.ic_launcher);

        setSupportActionBar(toolbar);
        //设置返回键
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //创建菜单，加载菜单文件
        getMenuInflater().inflate(R.menu.actionbar_test_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //给菜单按钮添加响应事件
        switch (item.getItemId()){
            case R.id.menuItem02:
                Toast.makeText(this,"菜单按钮项二被选中了",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem03:
                Toast.makeText(this,"菜单按钮项三被选中了",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem04:
                Toast.makeText(this,"菜单按钮项四被选中了",Toast.LENGTH_SHORT).show();
                return true;
            //返回键的按钮的响应事件
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
