package com.example.wanghui.androidstudy.interaction;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wanghui on 2016/9/8.
 * 局部加载
 */
public class PartLoadView extends View {
    private BitmapRegionDecoder mDecoder;

    public PartLoadView(Context context) {
        super(context);
        init(context);
    }

    public PartLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PartLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PartLoadView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context){

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
