package com.example.wanghui.androidstudy.view;

import android.content.Intent;
import android.view.View;

import com.example.wanghui.androidstudy.base.AbsSplashActivity;
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
                case R.id.btn_keep_moving:
                    goKeepMovingActivity();
                    break;
                case R.id.btn_gradient_view:
                    goGradientViewActivity();
                    break;
                case R.id.btn_power_view:
                    goPowerImageViewActivity();
                    break;
                case R.id.btn_textview_scale:
                    goTextSizeScaleActivity();
                    break;
                case R.id.btn_porterduff:
                    goPorterduffDemoActivity();
                    break;
                case R.id.btn_test_config:
                    goTestConfigChangeActivity();
                    break;
            }
        }
    };

    private void goTestConfigChangeActivity() {
        Intent intent = new Intent(this, TestConfigChangeActivity.class);
        intent.putExtra("id", 12345678);
        startActivity(intent);
    }

    private void goPorterduffDemoActivity() {
        Intent intent = new Intent(this, PorterduffDemoActivity.class);
        startActivity(intent);
    }

    private void goGradientViewActivity() {
        Intent intent = new Intent(this, GradientViewTestActivity.class);
        startActivity(intent);
    }

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
        initListener(R.id.btn_keep_moving);
        initListener(R.id.btn_gradient_view);
        initListener(R.id.btn_power_view);
        initListener(R.id.btn_textview_scale);
        initListener(R.id.btn_porterduff);
        initListener(R.id.btn_test_config);
    }

    private void goScrollTestActivity(){
        Intent intent = new Intent(this, ScrollTestActivity.class);
        startActivity(intent);
    }

    private void goKeepMovingActivity(){
        Intent intent = new Intent(this, KeepMovingActivity.class);
        startActivity(intent);
    }

    private void goPowerImageViewActivity(){
        Intent intent = new Intent(this, PowerImageViewTestActivity.class);
        startActivity(intent);
    }

    private void goTextSizeScaleActivity(){
        Intent intent = new Intent(this, TextSizeScaleActivity.class);
        startActivity(intent);
    }

}
