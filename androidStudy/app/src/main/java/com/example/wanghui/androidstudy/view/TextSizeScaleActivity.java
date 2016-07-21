package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/7/20.
 */
public class TextSizeScaleActivity extends Activity {
    private Button mBtnZoomIn;
    private Button mBtnZoomOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_size_scale);
        mBtnZoomIn = (Button) findViewById(R.id.btn_zoom_in);
        mBtnZoomOut = (Button) findViewById(R.id.btn_zoom_out);
        setListener();
    }

    private void refreshView(){
        setContentView(R.layout.activity_text_size_scale);
        mBtnZoomIn = (Button) findViewById(R.id.btn_zoom_in);
        mBtnZoomOut = (Button) findViewById(R.id.btn_zoom_out);
        setListener();
    }

    private void setListener() {
        final Resources res = getResources();
//        final Configuration config = res.getConfiguration();

        mBtnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = res.getConfiguration();
                config.fontScale = 0.5f;
                DisplayMetrics metrics = res.getDisplayMetrics();
                res.updateConfiguration(config, metrics);
                refreshView();
            }
        });

        mBtnZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = res.getConfiguration();
                config.fontScale = 2.0f;//缩放比例，3.0表示放大3倍
                DisplayMetrics metrics = res.getDisplayMetrics();
                res.updateConfiguration(config, metrics);
                refreshView();
            }
        });
    }
}
