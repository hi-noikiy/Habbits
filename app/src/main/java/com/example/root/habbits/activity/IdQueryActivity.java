package com.example.root.habbits.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.habbits.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 2016/5/17.
 */
public class IdQueryActivity extends Activity {
    private static final String TAG = "IdQueryActivity";
    private Handler handler;
    private Button button;
    private TextView textView;
    private EditText editText;
    private StringBuilder sb;
    private String httpUrl = "http://apis.baidu.com/apistore/idservice/id";
    private String httpArg = "id=";
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.id_search);

        init();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        result = handleJsonString((String)msg.obj);
                        Log.d(TAG,result);
                        textView.setText(result);
                        break;
                }
                super.handleMessage(msg);
            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String id = editText.getText().toString();
                        String result = httpArg + id;
                        Message ms = new Message();
                        ms.what = 1;
                        Log.d(TAG,result);
                        ms.obj  = request(httpUrl,result);
                        handler.sendMessage(ms);
                    }
                }).start();
            }
        });

    }

    private String handleJsonString(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONObject data= jsonObject.getJSONObject("retData");
            if(data.getString("sex").equals("M")){
                sb.append("性别：男\n");
            }else if(data.getString("sex").equals("F")){
                sb.append("性别：女\n");
            }else{
                sb.append("性别：未知\n");
            }
            sb.append("出生日期：" + data.getString("birthday") + "\n");
            sb.append("住址：" + data.getString("address"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    private void init() {
        sb = new StringBuilder();
        editText = (EditText) findViewById(R.id.ed_id_input);
        button = (Button) findViewById(R.id.bt_search);
        textView = (TextView) findViewById(R.id.tv_show);
    }

    public static String request(String httpUrl, String httpArg) {
        Log.d(TAG,httpArg);
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "b21d2c9540d3199f2cb2dac3ccdad4d2");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}


