package com.example.wanghui.androidstudy.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

/**
 * Created by wanghui on 2016/5/4.
 */
public class HandlerMsgActivity extends FragmentActivity{
    private TextView mTextView;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mTextView.setText(msg.obj.toString());
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        addContentView(mTextView, new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
        sendMsg();
    }

    private void sendMsg() {
        handler.obtainMessage(1, "haha").sendToTarget();
    }

}
