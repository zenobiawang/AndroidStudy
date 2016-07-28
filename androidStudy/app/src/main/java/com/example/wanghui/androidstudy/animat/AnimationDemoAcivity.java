package com.example.wanghui.androidstudy.animat;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 16/7/27.
 */
public class AnimationDemoAcivity extends Activity {
    private Object mObject;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void demo1(){
        ObjectAnimator.ofFloat(mObject, "translationY", 0, 100).start();
    }

    private void demo2(){
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(
                ObjectAnimator.ofFloat(mObject, "translationY", 0, 100),
                ObjectAnimator.ofFloat(mObject, "translationY", 100, 0)
        );
        set.setDuration(500)   //针对每个子动画
                .start();
    }

    private void demo3(){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_demo);
        set.setTarget(mObject);
        set.start();
    }

    private void demo4(){
        ObjectAnimator animator = new ObjectAnimator();
        animator.setTarget(mObject);
        animator.setPropertyName("X");
        animator.setFloatValues(0, 100);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });
        animator.start();
    }

    private void demo5(){
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator evaluator = new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // TODO: 16/7/28 1,根据时间流逝的比例得到属性值改变的比例 2,根据属性改变比例计算改变后的属性值 3,设置属性值
                float fraction = animation.getAnimatedFraction();  //动画已执行的百分比
                mTv.getLayoutParams().width = evaluator.evaluate(fraction, 0, 500);
                mTv.requestLayout();
            }
        });
        animator.setDuration(500).start();
    }

}
