package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/6/22.
 */
public class KeepMovingActivity extends Activity {
    int width = 0;
    int recycleWidth = 0;
    boolean isOver = false;
    private TextView mTvMoving;
    private TextView mTvRecycleMoving;
    private Button mBtnBegin;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    width += 100;
                    ViewGroup.LayoutParams layoutParams = mTvMoving.getLayoutParams();
                    layoutParams.width = width;
                    mTvMoving.setLayoutParams(layoutParams);
                    mTvMoving.requestLayout();
                    Log.d("wh", "mTvMoving--" + mTvMoving.getMeasuredWidth() + "--width--" + width);
                    break;
                case 2:
                    recycleWidth += 5;
                    if (recycleWidth > mTvMoving.getMeasuredWidth()){
                        recycleWidth = 0;
                        isOver = true;
                    }
                    ViewGroup.LayoutParams recycleLayoutParams = mTvRecycleMoving.getLayoutParams();
                    if (isOver){
                        recycleLayoutParams.width = mTvMoving.getMeasuredWidth();
                        isOver = false;
                    }else {
                        recycleLayoutParams.width = recycleWidth;
                    }
                    mTvRecycleMoving.setLayoutParams(recycleLayoutParams);
                    mTvRecycleMoving.requestLayout();
                    Log.d("wh", "mRecycleMoving--" + mTvRecycleMoving.getMeasuredWidth() + "--recycleWidth--" + recycleWidth);

                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downing_view);
        mTvMoving = (TextView) findViewById(R.id.tv_moving);
        mTvRecycleMoving = (TextView) findViewById(R.id.tv_recycle_moving);
        mBtnBegin = (Button) findViewById(R.id.btn_begin);
        mBtnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMoving();
            }
        });

    }

    private void startMoving(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        Thread.sleep(1000);
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
                        Thread.sleep(5);
                    }catch (InterruptedException e){

                    }
                    mHandler.sendEmptyMessage(2);
                }
            }
        }).start();
    }
}
