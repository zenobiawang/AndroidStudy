package com.example.wanghui.androidstudy.animat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/8/4.
 * 火箭升空的动画
 */
public class RocketLaunchAnimationActivity extends Activity {
    private ImageView mIvCloud1;
    private ImageView mIvCloud2;
    private ImageView mIvRocket;
    private ImageView mIvRocketFire;
    private RelativeLayout mRlRocket;
    private boolean mHasFocus = false;
    private Drawable[] rockets;
    private Drawable[] fires;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recket_launch);
        initView();
//        initAnimator();
        initData();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        rockets = new Drawable[]{getDrawable(R.drawable.hj0001), getDrawable(R.drawable.hj0002),
                getDrawable(R.drawable.hj0003), getDrawable(R.drawable.hj0004), getDrawable(R.drawable.hj0005)};
        fires = new Drawable[]{getDrawable(R.drawable.fire0001), getDrawable(R.drawable.fire0002),
                getDrawable(R.drawable.fire0003), getDrawable(R.drawable.fire0004), getDrawable(R.drawable.fire0005)};
    }

    private void initView() {
        mIvCloud1 = (ImageView) findViewById(R.id.iv_cloud1);
        mIvCloud2 = (ImageView) findViewById(R.id.iv_cloud2);
        mIvRocket = (ImageView) findViewById(R.id.iv_rocket);
        mIvRocketFire = (ImageView) findViewById(R.id.iv_rocket_fire);
        mRlRocket = (RelativeLayout) findViewById(R.id.rl_rocket);
    }

    private void initAnimator(){
        mRlRocket.setVisibility(View.VISIBLE);
        ObjectAnimator cloudAnimator1 = ObjectAnimator.ofFloat(mIvCloud1, "translationX", 0, -50).setDuration(500);
        ObjectAnimator cloudAnimator2 = ObjectAnimator.ofFloat(mIvCloud2, "translationX", 0, 50).setDuration(500);
        ObjectAnimator cloudAnimator3 = ObjectAnimator.ofFloat(mIvCloud1, "translationX", -50, -100).setDuration(2000);
        ObjectAnimator cloudAnimator4 = ObjectAnimator.ofFloat(mIvCloud2, "translationX", 50, 100).setDuration(2000);
        ObjectAnimator cloudAnimator5 = ObjectAnimator.ofFloat(mIvCloud1, "alpha", 1, 0).setDuration(500);
        ObjectAnimator cloudAnimator6 = ObjectAnimator.ofFloat(mIvCloud2, "alpha", 1, 0).setDuration(500);
        final AnimatorSet cloudSet = new AnimatorSet();
        AnimatorSet cloudSetFirst = new AnimatorSet();
        AnimatorSet cloudSetSecond = new AnimatorSet();
        cloudSetSecond.playTogether(cloudAnimator1, cloudAnimator2);
        cloudSetFirst.play(cloudAnimator3).with(cloudAnimator4).after(cloudSetSecond);
        cloudSet.play(cloudAnimator5).with(cloudAnimator6).after(cloudSetFirst);
//        cloudSet.play(cloudAnimator5).with(cloudAnimator6).after(cloudAnimator3).with(cloudAnimator4).after(cloudAnimator1).with(cloudAnimator2);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mRlRocket, "translationY", mRlRocket.getHeight(), 0).setDuration(500);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            boolean cloudAniDoing = false;
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if ((mIvCloud1.getTop() + mIvCloud1.getHeight()) < mRlRocket.getTop()
                        && !cloudAniDoing){
                    cloudSet.start();
                    cloudAniDoing = true;
                }
            }
        });
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mRlRocket, "translationY", 0, -100).setDuration(2000);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int temp = 0;
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                temp ++;
                if (temp > 8){
                    temp = 0;
                }
                mIvRocket.setImageDrawable(rockets[temp/2]);
                mIvRocketFire.setImageDrawable(fires[temp/2]);
            }
        });
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mRlRocket, "translationY", -100, -mRlRocket.getTop() - mRlRocket.getHeight()).setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.play(animator3).after(animator2).after(animator1);
        set.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !mHasFocus){
            mHasFocus = true;
            initAnimator();
        }
    }
}
