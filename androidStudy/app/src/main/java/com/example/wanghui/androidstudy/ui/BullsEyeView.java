package com.example.wanghui.androidstudy.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wanghui on 2016/4/5.
 * 自定义View
 */
public class BullsEyeView extends View {
    private Paint mPaint;
    private Point mCenter;
    private float mRadius;
    public BullsEyeView(Context context) {
        super(context);
        init();
    }

    public BullsEyeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BullsEyeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public BullsEyeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        mPaint = new Paint();
        mCenter = new Point();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width, height;
        int contentWidth = 100;
        int contentHeight = 100;
        width = getMeasurement(widthMeasureSpec, contentWidth);
        height = getMeasurement(heightMeasureSpec, contentHeight);
        setMeasuredDimension(width, height);
    }

    private int getMeasurement(int measureSpec, int contentSize) {
        int spaceSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)){
            case MeasureSpec.AT_MOST:
                return Math.min(spaceSize, contentSize);
            case MeasureSpec.EXACTLY:
                return spaceSize;
            case MeasureSpec.UNSPECIFIED:
                return contentSize;
            default:
                return 0;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh){
            mCenter.x = w/2;
            mCenter.y = h/2;
            mRadius = Math.min(mCenter.x, mCenter.y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.RED);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.8f, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.6f, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.4f, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.1f, mPaint);
    }
}
