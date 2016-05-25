package com.example.wanghui.androidstudy.expandable;

import android.annotation.TargetApi;
import android.content.Context;
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
    private boolean mExpandableFirst = false;
    private boolean mIsExpanded = false;
    private boolean mShouldExpand = false;
    private Map<Integer, Integer> mChildrenVisibleStates = new HashMap<>();
    private int mExpandHeight;
    private View mExpandView;
    private int mChildrenCount;
    private int mViewGonePosition;

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
        if (mExpandableFirst){
            int totalHeight = 0;
            mChildrenCount = getChildCount();
            storageStates();
            for (int i = 0; i < mChildrenCount; i ++){
                View childView = getChildAt(i);
                int visibility = childView.getVisibility();
                if (visibility == VISIBLE || visibility == INVISIBLE){
                    totalHeight += childView.getMeasuredHeight();
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) childView.getLayoutParams();
                    totalHeight += lp.topMargin;
                    totalHeight += lp.bottomMargin;
                    if (totalHeight > mExpandHeight){
                        mViewGonePosition = i;
                        mShouldExpand = true;
                        break;
                    }
                }
            }
            mExpandableFirst = false;
            if (mShouldExpand){
                setChildrenGone(mViewGonePosition);
                addExpandView(mExpandView);
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void storageStates() {
        for (int i = 0; i < mChildrenCount; i ++){
            mChildrenVisibleStates.put(i, getChildAt(i).getVisibility());
        }
    }

    private void setChildrenGone(int fromPosition){
        for (int j = fromPosition; j < mChildrenCount; j ++){
            getChildAt(j).setVisibility(GONE);
        }
    }

    private void recoverChildren(int fromPosition){
        for (int i = fromPosition; i < mChildrenCount; i ++){
            int visibilty = mChildrenVisibleStates.get(i);
            if (visibilty == VISIBLE){
                getChildAt(i).setVisibility(VISIBLE);
            }else if (visibilty == INVISIBLE){
                getChildAt(i).setVisibility(INVISIBLE);
            }else if (visibilty == GONE){
                getChildAt(i).setVisibility(GONE);
            }
        }
        requestLayout();
    }


    private void addExpandView(View view){
        if (view == null){
            return;
        }
        removeView(view);
        addView(view);
    }

    public void setExpandable(int height, View expandView, ExpandChangeListener expandChangeListener){
        if (height <= 0){
            return;
        }
        setExpandableView(expandView, expandChangeListener);
        mExpandHeight = height;
        mExpandableFirst = true;
        requestLayout();
    }

    public void setExpandableView(View expandView, final ExpandChangeListener expandChangeListener){
        mExpandView = expandView;
        mExpandView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsExpanded){
                    setChildrenGone(mViewGonePosition);
                    mIsExpanded = false;
                    expandChangeListener.onClose(mExpandView);
                }else {
                    recoverChildren(mViewGonePosition);
                    mIsExpanded = true;
                    expandChangeListener.onExpand(mExpandView);
                }
            }
        });
    }

    public interface ExpandChangeListener{
        void onExpand(View expandView);
        void onClose(View expandView);
    }

}
