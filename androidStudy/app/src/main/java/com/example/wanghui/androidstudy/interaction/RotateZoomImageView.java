package com.example.wanghui.androidstudy.interaction;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * Created by wanghui on 2016/4/8.
 */
public class RotateZoomImageView extends ImageView {
    private ScaleGestureDetector mScaleGestureDetector;
    private Matrix mMatrix;
    private int mLastAngle = 0;
    private int mPivotX, mPivotY;

    public RotateZoomImageView(Context context) {
        super(context);
        init(context);
    }

    public RotateZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RotateZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public RotateZoomImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleListener);
        mMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
    }

    private ScaleGestureDetector.SimpleOnScaleGestureListener mScaleListener = new ScaleGestureDetector.SimpleOnScaleGestureListener(){
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            mMatrix.postScale(scaleFactor, scaleFactor, mPivotX, mPivotY);
            setImageMatrix(mMatrix);
            return true;
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int translateX = Math.abs(w - getDrawable().getIntrinsicWidth())/2;
        int translateY = Math.abs(h - getDrawable().getIntrinsicWidth())/2;
        mMatrix.setTranslate(translateX, translateY);
        setImageMatrix(mMatrix);
        mPivotX = w/2;
        mPivotY = h/2;
    }

    private boolean doRotationEvent(MotionEvent event){
        float deltaX = event.getX(0) - event.getX(1);
        float deltaY = event.getY(0) - event.getY(1);
        double radians = Math.atan(deltaY/deltaX);
        int degress = (int) (radians*180/Math.PI);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastAngle = degress;
                break;
            case MotionEvent.ACTION_MOVE:
                if ((degress - mLastAngle) > 45){
                    mMatrix.postRotate(-5, mPivotX, mPivotY);
                }else if ((degress - mLastAngle) < -45){
                    mMatrix.postRotate(5, mPivotX, mPivotY);
                }else {
                    mMatrix.postRotate(degress - mLastAngle, mPivotX, mPivotY);
                }
                setImageMatrix(mMatrix);
                mLastAngle = degress;
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            return true;
        }

        switch (event.getPointerCount()){
            case 3:
                return mScaleGestureDetector.onTouchEvent(event);
            case 2:
                return doRotationEvent(event);
            default:
                return super.onTouchEvent(event);
        }
    }
}
