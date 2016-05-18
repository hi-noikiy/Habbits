package com.example.root.habbits.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.root.habbits.R;
import com.example.root.habbits.jni.HelloNDK;

/**
 * Created by root on 2016/5/13.
 */
public class NDKTestActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ndk_test);

        textView = (TextView) findViewById(R.id.helloNDK);
        //调用native方法，显示字符串
        textView.setText(HelloNDK.getCLangString());
    }


}
