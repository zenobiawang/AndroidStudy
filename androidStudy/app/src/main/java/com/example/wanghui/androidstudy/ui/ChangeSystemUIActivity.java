package com.example.wanghui.androidstudy.ui;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/4/5.
 * 应用程序体验需要对显示进行控制，移除各种系统修饰，例如状态栏和软件导航按钮
 */
public class ChangeSystemUIActivity extends FragmentActivity {
    private int mTest1;
    private BullsEyeView mBullsEyeView;
    private LinearLayout mLlAnimationContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changesystemui);
        mBullsEyeView = (BullsEyeView) findViewById(R.id.biv);

        initAnimation();
    }

    private void initAnimation() {
        mLlAnimationContainer = (LinearLayout) findViewById(R.id.ll_layout_animetion_container);
        LayoutTransition layoutTransition = new LayoutTransition();
        mLlAnimationContainer.setLayoutTransition(layoutTransition);

        ObjectAnimator appearingAnimator = ObjectAnimator.ofFloat(null, "rotationX", 90f, 0f).setDuration(layoutTransition.getDuration(LayoutTransition.APPEARING));
        layoutTransition.setAnimator(LayoutTransition.APPEARING, appearingAnimator);

        PropertyValuesHolder pvhSlide = PropertyValuesHolder.ofFloat("y", 0, 1);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f, 1f);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f, 1f);
        ValueAnimator changingAppearingAnim = ObjectAnimator.ofPropertyValuesHolder(this, pvhScaleX, pvhScaleY, pvhSlide);
        changingAppearingAnim.setDuration(layoutTransition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changingAppearingAnim);
    }

    public void onToggleClick(View view){
//        int newVis;
//        newVis = getTest3();
//        view.setSystemUiVisibility(newVis);

//        test4();
        test5(); //布局变化时的动画
    }


    private int getTest1() {   //夜间模式 调暗屏幕的导航控件，（并没有看见效果惹）
        return View.SYSTEM_UI_FLAG_LOW_PROFILE;
    }

    private int getTest2(){    //隐藏导航控件
        return View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    private int getTest3(){   //全屏UI模式
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    private void test4(){ //简单过渡动画
        if (mBullsEyeView.getAlpha() > 0f){
            mBullsEyeView.animate().alpha(0f).translationX(1000f).setDuration(500);
        }else {
            mBullsEyeView.setTranslationX(0f);
            mBullsEyeView.animate().alpha(1f);
        }
    }

    private void test5(){
        Button button = new Button(this);
        button.setText("瞧瞧");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlAnimationContainer.removeView(v);
            }
        });
        mLlAnimationContainer.addView(button);
    }

}
