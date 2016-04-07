package com.example.wanghui.androidstudy.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/4/7.
 */
public class ScrollActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        PerspectiveScrollContentView view = new PerspectiveScrollContentView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        for (int i = 0; i < 20; i ++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            view.addView(imageView);
        }

        horizontalScrollView.addView(view);
        setContentView(horizontalScrollView);
    }
}
