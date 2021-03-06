package com.example.wanghui.androidstudy.media;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by wanghui on 2016/4/15.
 */
public class TimerView extends View {
    private long mDuration = 20000;
    private ObjectAnimator mObjectAnimator;
    private int mWidth;
    private boolean isMoving = false;
    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidthMeasureSpec = widthMeasureSpec;
        if (!isMoving){
            if (mWidth == 0){
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
            }
            mWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.getMode(widthMeasureSpec));
        }
        super.onMeasure(mWidthMeasureSpec, heightMeasureSpec);
    }

    private void initAnimator(){
        mObjectAnimator = ObjectAnimator.ofInt(this, "layoutWidth", 0, mWidth);
        mObjectAnimator.setDuration(mDuration);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
    }

    public void setLayoutWidth(int width){
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        setLayoutParams(params);
    }

    public void startMoving(){
        if (mObjectAnimator == null){
            initAnimator();
        }
        mObjectAnimator.start();
        isMoving = true;
    }

    public void reset(){
        setLayoutWidth(0);
    }

    /**
     * 停止动画
     * @return 返回当前动画进行到的时间
     */
    public long stopMoving(){
        mObjectAnimator.cancel();
        isMoving = false;
        return mObjectAnimator.getCurrentPlayTime();
    }

    /**
     * 设置动画的时长，默认是20s
     * @param duration
     */
    public void setDuration(long duration){
        this.mDuration = duration;
    }

    public long getDuration(){
        return mDuration;
    }
}
