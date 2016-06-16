package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/6/16.
 */
public class ScrollTestActivity extends Activity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
        mTextView = (TextView) findViewById(R.id.tv_content);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.scrollBy(20, 20);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
