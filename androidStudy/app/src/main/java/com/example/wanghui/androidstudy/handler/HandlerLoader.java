package com.example.wanghui.androidstudy.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by wanghui on 2016/5/5.
 */
public class HandlerLoader extends HandlerThread {
    Handler mHandler;
    public HandlerLoader(String name) {
        super(name);
    }

    public HandlerLoader(String name, int priority) {
        super(name, priority);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }
}
