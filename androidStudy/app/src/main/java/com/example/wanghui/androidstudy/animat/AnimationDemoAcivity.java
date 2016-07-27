package com.example.wanghui.androidstudy.animat;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;

/**
 * Created by wanghui on 16/7/27.
 */
public class AnimationDemoAcivity extends Activity {
    private Object mObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void demo1(){
        ObjectAnimator.ofFloat(mObject, "translationY", 0, 100).start();
    }

    private void demo2(){
        ValueAnimator.ofFloat(mObject, "translationY", 0, 100).start();
    }

}
