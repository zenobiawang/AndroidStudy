package com.example.wanghui.androidstudy.interaction;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wanghui on 2016/9/8.
 * 局部加载
 */
public class PartLoadView extends View {
    private BitmapRegionDecoder mDecoder;
    private Context mContext;

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
        mContext = context;
    }

    public void setInputStream(InputStream is){
        try {
            // TODO: 2016/9/12 首次展示
            mDecoder = BitmapRegionDecoder.newInstance(is, false);
            BitmapFactory.Options tempOptions = new BitmapFactory.Options();
            tempOptions.inJustDecodeBounds = false;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 2016/9/12 获取屏幕尺寸
    private int getPhoneWidth(){
        return 1;
    }
    // TODO: 2016/9/12 获取屏幕尺寸
    private int getPhoneHeight(){
        return 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
