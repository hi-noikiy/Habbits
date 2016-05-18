package com.example.root.habbits.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.habbits.R;
import com.example.root.habbits.fragment.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016/5/14.
 */
public class DrawerLayoutActivity extends ActionBarActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView listView;
    private ArrayAdapter arrayAdapter;

    //TabLayout的数据
    public static final String[] tabTitle = new String[]{"综艺", "体育", "新闻", "热点", "头条", "军事", "历史", "科技", "人文", "地理"};
    //DrawerLayout左侧滑菜单中包含的List中的数据
    private static final String [] lvs = {"列表项01","列表项02","列表项03","列表项04"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        init();


        toolbar.setTitle("标题栏");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //这个东西是一个drawerLayout的监听器
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lvs);
        listView.setAdapter(arrayAdapter);

        //用于ViewPager使用
        List<Fragment> fragments = new ArrayList<>();
        for(int i = 0;i<tabTitle.length;i++){
            fragments.add(TabLayoutFragment.newInstance(i+1));
        }

        tabAdapter = new TabAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(tabAdapter);
        //将TabLayout与ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager);
        //将TabLayout设置为可滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        listView = (ListView) findViewById(R.id.lv_left_menu);
        tabLayout = (TabLayout) findViewById(R.id.my_tableLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}

//自定义一个适配器
class TabAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //设置TabLayout标题
    @Override
    public CharSequence getPageTitle(int position) {
        return DrawerLayoutActivity.tabTitle[position]  ;
    }
}
