package com.example.root.habbits.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.habbits.R;

/**
 * Created by root on 2016/5/14.
 */
public class TabLayoutFragment extends Fragment {
    private static final String TABLAYOUT_FRAGMENT = "tab_fragment";
    private static int type;
    private TextView textView;

    public static TabLayoutFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putSerializable(TABLAYOUT_FRAGMENT,type);
        TabLayoutFragment fragment = new TabLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_fragment01,container,false);
        initViews(view);
        return view;
    }

    //每一行都添加一个findViewbyId是为了防止显示的都是最后一个
    private void initViews(View view) {
        textView = (TextView) view.findViewById(R.id.tab_text);

        switch (type){
            case 1:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是综艺Fragment");
                break;
            case 2:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是体育Fragment");
                break;
            case 3:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是新闻Fragment");
                break;
            case 4:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是热点Fragment");
                break;
            case 5:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是头条Fragment");
                break;
            case 6:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是军事Fragment");
                break;
            case 7:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是历史Fragment");
                break;
            case 8:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是科技Fragment");
                break;
            case 9:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是人文Fragment");
                break;
            case 10:
                textView = (TextView) view.findViewById(R.id.tab_text);
                textView.setText("这是地理Fragment");
                break;
        }
    }
}