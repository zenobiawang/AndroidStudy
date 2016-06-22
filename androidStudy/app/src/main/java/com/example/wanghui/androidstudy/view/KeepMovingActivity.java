package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/6/22.
 */
public class KeepMovingActivity extends Activity {
    private TextView mTvMoving;
    private TextView mTvRecycleMoving;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downing_view);
        mTvMoving = (TextView) findViewById(R.id.tv_moving);
        mTvRecycleMoving = (TextView) findViewById(R.id.tv_recycle_moving);
    }
}
