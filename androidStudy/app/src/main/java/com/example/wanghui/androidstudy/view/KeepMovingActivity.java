package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
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

        startMoving();
    }

    private void startMoving(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){

                    }
                    Message message = mHandler.obtainMessage(1);
                    mHandler.sendMessage(message);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                    mHandler.sendEmptyMessage(2);
                }
            }
        }).start();
    }
}
