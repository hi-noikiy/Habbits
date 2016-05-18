package com.example.root.habbits.activity;


import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.example.root.habbits.MyApplication;
import com.example.root.habbits.R;
import com.example.root.habbits.fragment.Fragment01;
import com.example.root.habbits.fragment.Fragment02;

import java.lang.reflect.Field;

/**
 * Created by root on 2016/5/9.
 */
public class ActionBarTestActivity extends ActionBarActivity {
    private static final String TAG = "ActionBarTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar_test);

        //启动ActionBar导航功能，允许点击后退按钮
        //3.0之后才有ActionBar，所以要设置最小SDK为11，否则报空指针异常
        //但是我把最小SDK设置为11之后依旧不行，我就换了不用getActionBar,换用getSupportActionBar
        //但是不能继承自Activity，只能继承自ActionBarActivity
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //因为机型不同，有的手机有物理menu的话，就不显示overFlow
        setOverFlowAlwaysShow();

        //开启tab导航模式并且添加tab
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab = actionBar
                .newTab()
                .setText("Tab01")
                .setTabListener(new TabListener<Fragment01>("Fragment01",this, Fragment01.class));
        actionBar.addTab(tab);

        tab = actionBar
                .newTab()
                .setText("Tab02")
                .setTabListener(new TabListener<Fragment02>("Fragment02",this, Fragment02.class));
        actionBar.addTab(tab);
    }

    /**
     * 通过反射，使sHasPermanentMenuKey的值为false，这样的话，就永远不使用物理menu，只使用overflow
     */
    private void setOverFlowAlwaysShow() {
        try {
            ViewConfiguration configuration = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(configuration,false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置ActionBar上的菜单按钮
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_test_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        //我了个Fuck，想获得searchView必须使用MenuItemCompat，这个是supportv4支持的
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //下边可以配置searchView的属性，有时间再写

        //参考：http://zhidao.baidu.com/link?url=9p-69IGVwy4_Zpsvc7Xi9Nx_f4i43ypC6-gIWolZ0Ej2ed-zmYEv9B2L2lD7tiD1UJwimdbmHRe5Q8TVTGzQQsV0f36xpPZmdAe7G8vkTqG
        //我擦啊，这个问题困扰了我好久，其实是导包问题
        //ShareActionProvider在widget包里和support.v7.widget都有，ActionProvider是support.v4包里的,真混乱
        MyActionProvider provider = (MyActionProvider) MenuItemCompat.getActionProvider(shareItem);
        //provider.setContext(this);
        provider.setShareIntent(getDefaultIntent());


        //可以控制ActionView的展开和合并的时候显示进行不同的处理，或者显示不同的界面
        //我了个Fuck，添加ActionView的展开闭合监听事件必须使用MenuItemCompat，这个是supportv4支持的，坑爹，MenuItem被淘汰了
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(getApplicationContext(), "搜索ActionView被展开了", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(getApplicationContext(), "搜索ActionView被合并了", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //擦，忘了加super，然后无限递归栈溢出了
        return super.onCreateOptionsMenu(menu);
    }

    //给intent指定类型
    private Intent getDefaultIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        return intent;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuItem02:
                Toast.makeText(this,"菜单按钮项二被选中了",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem03:
                Toast.makeText(this,"菜单按钮项三被选中了",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem04:
                Toast.makeText(this,"菜单按钮项四被选中了",Toast.LENGTH_SHORT).show();
                return true;
            //后退按钮
            //FUCK!!原来是android.R.id.home,我只写了R.id.home，怪不得点击没反应
            case android.R.id.home:
                //调用最低要求是API 16
                //解决了，坑，又是空指针异常，调用了getParentActivityIntent()就空指针，需要加support
                Intent upIntent = getSupportParentActivityIntent();
                if(NavUtils.shouldUpRecreateTask(this,upIntent)){
                    //如果和父Activity不是一个Task的，新建一个Task
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }else{
                    //如果是在一个Task中，直接跳转，并清除父Activity之上的Activity
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this,upIntent);
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}


/**
 * 自定义了一个ActionProvider继承自ShareActionProvider(v7包的那个)
 * 思考：overFlow是不是一个重写的ActionProvider？
 */
class MyActionProvider extends ShareActionProvider{
    private final String TAG = "MyActionProvider";

    /**
     * Creates a new instance. ActionProvider classes should always implement a
     * constructor that takes a single Context parameter for inflating from menu XML.
     *
     * @param context Context for accessing resources.
     */
    public MyActionProvider(Context context) {
        //调用父类构造方法用在第一句
        super(context);
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        subMenu.add("子菜单01")
                .setIcon(R.drawable.ic_launcher)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //我傻了，点击这个按钮不弹出Toast，最初以为是context的问题，自己定义了一个Application，但是后来发现原来是忘了加show
                        Toast.makeText(MyApplication.getContext(), "子菜单01被点击了", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
        subMenu.add("子菜单02")
                .setIcon(R.drawable.ic_launcher)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MyApplication.getContext(), "子菜单02被点击了", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }
}

/**
 * Tab的监听器
 * @param <T>
 */
class TabListener<T extends Fragment> implements ActionBar.TabListener {
    //这个Fragment是v4包里的Fragment
    private Fragment mFragment;
    private final String tag;
    private final Activity mActivity;
    private final Class<T> mClass;

    TabListener(String tag, Activity mActivity, Class<T> mClass) {
        this.tag = tag;
        this.mActivity = mActivity;
        this.mClass = mClass;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(mFragment == null){
            mFragment = Fragment.instantiate(mActivity,mClass.getName());
            ft.add(android.R.id.content,mFragment,tag);
        }else{
            //不为空直接attach
            ft.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(mFragment != null)
            ft.detach(mFragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
