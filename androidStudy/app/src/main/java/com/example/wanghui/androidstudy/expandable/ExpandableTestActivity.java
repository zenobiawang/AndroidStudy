package com.example.wanghui.androidstudy.expandable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/5/24.
 */
public class ExpandableTestActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_test);
    }
}
