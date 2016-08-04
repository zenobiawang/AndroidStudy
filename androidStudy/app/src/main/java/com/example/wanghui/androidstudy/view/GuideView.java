package com.example.wanghui.androidstudy.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by wanghui on 2016/8/3.
 * 引导页
 */
public class GuideView extends View {
    private Context mContext;
    private int backgroundColor = Color.parseColor("#cc222222");
    private View mTarget;
    private float mRadius;
    private Drawable mGuideDrawable;

    public GuideView(Context context) {
        super(context);
        init(context);
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GuideView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Canvas tempCanvas = new Canvas();
//        Canvas tempCanvas = new Canvas();
//        Paint tempPaint = new Paint();
//        tempPaint.setColor(backgroundColor);
//        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), tempPaint);
//        Paint ciclePaint = new Paint();
//        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
//        ciclePaint.setXfermode(porterDuffXfermode);
//        ciclePaint.setAntiAlias(true);
//        ciclePaint.setColor(Color.TRANSPARENT);
//        canvas.drawCircle(100, 100, 50, ciclePaint);
        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas  = new Canvas(bitmap);
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);
        tempCanvas.drawRect(0, 0, tempCanvas.getWidth(), tempCanvas.getHeight(), backgroundPaint);
        Paint secPaint = new Paint();
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        secPaint.setXfermode(porterDuffXfermode);
        secPaint.setAntiAlias(true);
        tempCanvas.drawCircle(200, 200, 100, secPaint);
//        Bitmap bitmap1 = mGuideDrawable.
        canvas.drawBitmap(bitmap, 0, 0, backgroundPaint);
        bitmap.recycle();
    }
}
