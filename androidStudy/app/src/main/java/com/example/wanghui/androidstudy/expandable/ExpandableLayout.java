package com.example.wanghui.androidstudy.expandable;

import android.annotation.TargetApi;
import android.content.Context;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghui on 2016/5/24.  设置展开收起的高度、设置展开收起的UI
 */
public class ExpandableLayout extends LinearLayout {
    private boolean mExpandable = false;
    private Map<Integer, Integer> childrenVisibleStates = new HashMap<>();
    private int mExpandHeight;
    private View mExpandView;
    private View mRetractView;
    public ExpandableLayout(Context context) {
        super(context);
    }

    public ExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandableLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // TODO: 2016/5/24 判断是否显示展开收起、展开收起ui
        if (mExpandable){
            int totalHeight = 0;
            int count = getChildCount();
            for (int i = 0; i < count; i ++){
                View childView = getChildAt(i);
                int visibility = childView.getVisibility();
                childrenVisibleStates.put(i, visibility);
                if (visibility == VISIBLE || visibility == INVISIBLE){
                    totalHeight += childView.getMeasuredHeight();
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) childView.getLayoutParams();
                }
            }
        }

    }

    public void setExpandHeight(int height){
        if (height <= 0){
            return;
        }
        mExpandHeight = height;
        mExpandable = true;
        requestLayout();
    }

    public void setExpandableViews(View expandView, View retractView){
        mExpandView = expandView;
        mRetractView = retractView;
    }
}
