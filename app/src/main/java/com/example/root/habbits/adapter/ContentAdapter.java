package com.example.root.habbits.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.habbits.R;
import com.example.root.habbits.model.ContentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016/5/10.
 */
public class ContentAdapter extends BaseAdapter {

    private static final String TAG = "ContentAdapter";
    private List<ContentBean> mList = new ArrayList<ContentBean>();
    private LayoutInflater mInflater;
    private ContentBean bean;
    private Context mContext;

    public ContentAdapter(Context context,List<ContentBean> list){
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.call_list_item,null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.mCall = (Button) convertView.findViewById(R.id.mCall);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        bean = mList.get(position);

        viewHolder.tv_number.setText(bean.getNumber());
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //晓宇测试了下，老是拨打最后一个人的电话，后来我觉得是bean应该是指向最后一个人，跟
                //前边出现的问题一样，需要每次都获取新的bean的引用
                //setText，setImage之类的可以不用每次获取新的bean的引用，但是按钮监听事件需要，特别是
                //监听事件里用到bean的内容的时候（后发制人）
                call(mList.get(position).getNumber());
            }
        });

        return convertView;
    }

    class ViewHolder{
        public TextView tv_name;
        public TextView tv_number;
        public Button mCall;
    }


    public void call(String number){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("tel:" + number));
        mContext.startActivity(intent);
        //Log.d(TAG, number);
    }
}

