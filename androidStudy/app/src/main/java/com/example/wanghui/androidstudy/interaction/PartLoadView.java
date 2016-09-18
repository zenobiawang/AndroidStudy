package com.example.wanghui.androidstudy.interaction;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;

import com.example.wanghui.androidstudy.base.Log;

import java.io.IOException;

/**
 * Created by wanghui on 2016/9/8.
 * 局部加载
 */
public class PartLoadView extends View {
    private BitmapRegionDecoder mDecoder;
    private Context mContext;
    private BitmapFactory.Options mOptions;
    private byte[] mData;
    private Rect mRect;
    private ScaleGestureDetector mDetector;

    public PartLoadView(Context context) {
        super(context);
        init(context);
    }

    public PartLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public PartLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PartLoadView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);

    }

    private void init(Context context){
        mContext = context;
        mOptions = new BitmapFactory.Options();
        mRect = new Rect(0, 0, getWidth(), getHeight());
        setOnTouchListener(new MyTouchListener());
        mDetector = new ScaleGestureDetector(mContext, new MyScaleGestureListener());
    }

    public void setInputStream(byte[] bytes){
        try {
            // TODO: 2016/9/12 首次展示
            this.mData = bytes;
            BitmapRegionDecoder.newInstance(mData, 0, mData.length, false);
            mOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(mData, 0, mData.length, mOptions);
            int actualWidth = mOptions.outWidth;
            int actualHeight = mOptions.outHeight;
            mOptions.inJustDecodeBounds = false;
            mOptions.inSampleSize = findBestSampleSize(actualWidth, actualHeight, getMeasuredWidth(), getMeasuredHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int findBestSampleSize(
            int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
        double wr = (double) actualWidth / desiredWidth;
        double hr = (double) actualHeight / desiredHeight;
        double ratio = Math.min(wr, hr);
        float n = 1.0f;
        while ((n * 2) <= ratio) {
            n *= 2;
        }

        return (int) n;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (mData != null){
            Bitmap bitmap = mDecoder.decodeRegion(mRect, mOptions);
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    private class MyScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("wh------" + detector.getScaleFactor());
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return false;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

    private class MyTouchListener implements OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mDetector.onTouchEvent(event);
            return false;
        }
    }
}
