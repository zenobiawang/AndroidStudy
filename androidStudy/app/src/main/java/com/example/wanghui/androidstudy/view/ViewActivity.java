package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wanghui.androidstudy.AbsSplashActivity;
import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/6/16.
 */
public class ViewActivity extends AbsSplashActivity {
    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_scrollTest:
                    goScrollTestActivity();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_splash;
    }

    @Override
    protected View.OnClickListener getClickListener() {
        return mOnclickListener;
    }

    @Override
    protected void init() {
        initListener(R.id.btn_scrollTest);
    }

    private void goScrollTestActivity(){
        Intent intent = new Intent(this, ScrollTestActivity.class);
        startActivity(intent);
    }

}
