package com.example.wanghui.androidstudy.animat;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/8/1.
 */
public class AnimationTestActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        initAnimation();
    }

    private void initAnimation() {
        Button button = (Button) findViewById(R.id.btn_animation_test);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(button, "translationX", 0, 500).setDuration(500);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(button, "alpha", 1f, 0f).setDuration(500);
        set.play(animator2).after(animator1);
        set.start();
    }
}
