package com.example.wanghui.androidstudy.handler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

/**
 * Created by wanghui on 2016/5/4.
 */
public class HandlerMsgActivity extends FragmentActivity{
    private TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);

    }
}
