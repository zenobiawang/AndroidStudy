package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/8/10.
 */
public class TestConfigChangeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Log.d("androidStudy", "wh----onCreate");
    }
}
