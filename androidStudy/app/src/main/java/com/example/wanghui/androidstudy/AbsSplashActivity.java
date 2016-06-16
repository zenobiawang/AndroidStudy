package com.example.wanghui.androidstudy;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by wanghui on 2016/6/16.
 */
public abstract class AbsSplashActivity extends Activity {
    protected abstract int getLayoutId();
    protected abstract View.OnClickListener getClickListener();
    protected abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }
    protected void initListener(@IdRes int resId){
        View view = findViewById(resId);
        view.setOnClickListener(getClickListener());
    }
}
