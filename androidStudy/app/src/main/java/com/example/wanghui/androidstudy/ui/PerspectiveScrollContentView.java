package com.example.wanghui.androidstudy.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by wanghui on 2016/4/7.
 */
public class PerspectiveScrollContentView extends LinearLayout {
    private float SCALE_FACTOR = 0.7F;
    private float ANCHOR_Y = 0.5F;
    private float ANCHOR_X = 0.5F;

    public PerspectiveScrollContentView(Context context) {
        super(context);
        init();
    }

    public PerspectiveScrollContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PerspectiveScrollContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PerspectiveScrollContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setStaticTransformationsEnabled(true);
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        HorizontalScrollView scrollView = null;
        if (getParent() instanceof HorizontalScrollView){
            scrollView = (HorizontalScrollView) getParent();
        }

        if (scrollView == null){
            return false;
        }

        int chileCenter = getCenter(child);
        int viewCenter = getCenter(scrollView);

        float delta = Math.min(1.0f, Math.abs(chileCenter - viewCenter) / (float) viewCenter);
        float scale = Math.max(0.4f, 1.0f - (SCALE_FACTOR * delta));

        t.clear();
        t.getMatrix().setScale(scale, scale, child.getWidth()*ANCHOR_X, child.getHeight()*ANCHOR_Y);

        return true;


    }

    private int getCenter(View view){
        int[] position = new int[2];
        view.getLocationOnScreen(position);
        return position[0] + view.getWidth()/2;
    }
}
