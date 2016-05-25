package com.example.wanghui.androidstudy.expandable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/5/24.
 */
public class ExpandableTestActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_test);
        ExpandableLayout layout = (ExpandableLayout) findViewById(R.id.expand_layout);
        TextView textView = new TextView(this);
        textView.setText("展开");
        layout.setExpandable(100, textView, new ExpandableLayout.ExpandChangeListener() {
            @Override
            public void onExpand(View expandView) {
                ((TextView)expandView).setText("收起");
            }

            @Override
            public void onClose(View expandView) {
                ((TextView)expandView).setText("展开");
            }
        });
    }
}
