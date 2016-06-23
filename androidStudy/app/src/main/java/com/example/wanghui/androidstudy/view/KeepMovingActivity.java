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
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, RelativeLayout.LayoutParams.MATCH_PARENT);
                    mTvMoving.setLayoutParams(layoutParams);
                    Log.d("wh", "mTvMoving--" + mTvMoving.getMeasuredWidth());
                    break;
                case 2:
                    recycleWidth += 10;
                    if (recycleWidth > mTvRecycleMoving.getMeasuredWidth()){
                        recycleWidth = 0;
                        isOver = true;
                    }
                    RelativeLayout.LayoutParams recycleLayoutParams;
                    if (isOver){
                        recycleLayoutParams = new RelativeLayout.LayoutParams(mTvRecycleMoving.getMeasuredWidth(), RelativeLayout.LayoutParams.MATCH_PARENT);
                        isOver = false;
                    }else {
                        recycleLayoutParams = new RelativeLayout.LayoutParams(width + 10, RelativeLayout.LayoutParams.MATCH_PARENT);
                    }

                    mTvMoving.setLayoutParams(recycleLayoutParams);
                    Log.d("wh", "mRecycleMoving--" + mTvRecycleMoving.getMeasuredWidth());
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
