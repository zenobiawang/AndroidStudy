package com.example.wanghui.androidstudy.interaction;

import android.content.Intent;
import android.view.View;

import com.example.wanghui.androidstudy.R;
import com.example.wanghui.androidstudy.base.AbsSplashActivity;

/**
 * Created by wanghui on 2016/9/7.
 */
public class InteractionSplash extends AbsSplashActivity {
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_multi_touch:
                    goMultiActivity();
                    break;
            }
        }

    };

    private void goMultiActivity() {
        Intent intent = new Intent(this, MultiTouchActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_interaction_splash;
    }

    @Override
    protected View.OnClickListener getClickListener() {
        return mListener;
    }

    @Override
    protected void init() {
        initListener(R.id.btn_multi_touch);
        initListener(R.id.btn_part_load);
    }
}
