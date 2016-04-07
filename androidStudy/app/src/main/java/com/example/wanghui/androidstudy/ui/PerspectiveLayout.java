package com.example.wanghui.androidstudy.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by wanghui on 2016/4/7.
 */
public class PerspectiveLayout extends LinearLayout{
    public PerspectiveLayout(Context context) {
        super(context);
        init();
    }

    public PerspectiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PerspectiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PerspectiveLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setStaticTransformationsEnabled(true);
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        t.clear();
        if (getOrientation() == HORIZONTAL){
            float delta = 1.0f - ((float)child.getLeft()/getWidth());
            t.getMatrix().setScale(delta, delta, child.getWidth()/2, child.getHeight()/2);
        }else {
            float delta = 1.0f - ((float)child.getTop()/getHeight());
            t.getMatrix().setScale(delta, delta, child.getWidth()/2, child.getHeight()/2);
            t.setAlpha(delta);
        }
        return true;
    }
}
