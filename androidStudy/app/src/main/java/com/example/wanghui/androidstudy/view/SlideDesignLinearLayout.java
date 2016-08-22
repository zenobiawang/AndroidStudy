package com.example.wanghui.androidstudy.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wanghui.androidstudy.base.Log;

/**
 * Created by wanghui on 2016/8/22.
 */
public class SlideDesignLinearLayout extends LinearLayout implements NestedScrollingParent{
    public SlideDesignLinearLayout(Context context) {
        super(context);
    }

    public SlideDesignLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideDesignLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SlideDesignLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.d("wh-----onStartNestedScroll");
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.d("wh-----onNestedPreScroll");
//        boolean hiddenTop = dy > 0 && getScrollY() <
    }
}
