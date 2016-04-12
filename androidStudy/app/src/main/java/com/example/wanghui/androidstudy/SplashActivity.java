package com.example.wanghui.androidstudy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.wanghui.androidstudy.interaction.MultiTouchActivity;
import com.example.wanghui.androidstudy.media.VideoCapture2Activity;
import com.example.wanghui.androidstudy.media.VideoCaptureActivity;
import com.example.wanghui.androidstudy.testrxjava.TestRxJavaActivity;
import com.example.wanghui.androidstudy.ui.ChangeSystemUIActivity;
import com.example.wanghui.androidstudy.ui.ScrollActivity;

import java.io.File;

/**
 * Created by wanghui on 2016/4/5.
 */
public class SplashActivity extends FragmentActivity {
    private Button mButtonChangeSystemUI;
    private Button mButtonScaleUI;
    private Button mButtonTestRxJava;
    private Button mButtonMultiTouch;
    private Button mButtonMediaVideo;
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

        mButtonTestRxJava = (Button) findViewById(R.id.btn_test_rxjava);
        setClickListener(mButtonTestRxJava);

        mButtonMultiTouch = (Button) findViewById(R.id.btn_multi_touch);
        setClickListener(mButtonMultiTouch);

        mButtonMediaVideo = (Button) findViewById(R.id.btn_media_video);
        setClickListener(mButtonMediaVideo);
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
                case R.id.btn_test_rxjava:
                    goTestRxJava();
                    break;
                case R.id.btn_multi_touch:
                    goMultiTouch();
                    break;
                case R.id.btn_media_video:
                    goMediaVideo();
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

    private void goTestRxJava(){
        Intent intent = new Intent(this, TestRxJavaActivity.class);
        startActivity(intent);
    }

    private void goMultiTouch(){
        Intent intent = new Intent(this, MultiTouchActivity.class);
        startActivity(intent);
    }

    private void goMediaVideo(){
        Intent intent = new Intent(this, VideoCaptureActivity.class);
        startActivity(intent);

//        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        File recordOutput = new File(Environment.getExternalStorageDirectory(), "recorded_video.mp4");
//        Uri uri = Uri.fromFile(recordOutput);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
//        startActivity(intent);

    }
}
