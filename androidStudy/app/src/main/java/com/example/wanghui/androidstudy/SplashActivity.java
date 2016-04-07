package com.example.wanghui.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.wanghui.androidstudy.ui.ChangeSystemUIActivity;
import com.example.wanghui.androidstudy.ui.ScrollActivity;

/**
 * Created by wanghui on 2016/4/5.
 */
public class SplashActivity extends FragmentActivity {
    private Button mButtonChangeSystemUI;
    private Button mButtonScaleUI;
    private SplashClickListener mSplashClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashClickListener = new SplashClickListener();
        initView();
    }

    private void initView() {
        mButtonChangeSystemUI = (Button) findViewById(R.id.btn_changeSystemUI);
        setClickListener(mButtonChangeSystemUI);

        mButtonScaleUI = (Button) findViewById(R.id.btn_changeScaleUI);
        setClickListener(mButtonScaleUI);
    }

    private void setClickListener(View view) {
        view.setOnClickListener(mSplashClickListener);
    }


    private class SplashClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_changeSystemUI:
                    goChangeSystemUI();
                    break;
                case R.id.btn_changeScaleUI:
                    goChangeScaleUI();
                    break;
            }
        }
    }

    private void goChangeSystemUI() {
        Intent intent = new Intent(this, ChangeSystemUIActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_enter);
    }

    private void goChangeScaleUI(){
        Intent intent = new Intent(this, ScrollActivity.class);
        startActivity(intent);
    }
}
