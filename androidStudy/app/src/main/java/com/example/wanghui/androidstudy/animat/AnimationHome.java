package com.example.wanghui.androidstudy.animat;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.wanghui.androidstudy.R;
import com.example.wanghui.androidstudy.base.AbsSplashActivity;

/**
 * Created by wanghui on 16/7/27.
 */
public class AnimationHome extends AbsSplashActivity{
    private AnimationOnClickListener mlistener = new AnimationOnClickListener();
    public class AnimationOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_animation_demo:
                    goAnimationDemo();
                    break;
                case R.id.btn_animation_test:
                    goAnimationTest();
                    break;
            }
        }
    }

    private void goAnimationTest() {
        Intent intent = new Intent(this, AnimationTestActivity.class);
        startActivity(intent);
    }

    private void goAnimationDemo() {
        Intent intent = new Intent(this, AnimationDemoAcivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected View.OnClickListener getClickListener() {
        return mlistener;
    }

    @Override
    protected void init() {
        initListener(R.id.btn_animation_demo);
        initListener(R.id.btn_animation_test);
    }
}
