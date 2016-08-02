package com.example.wanghui.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wanghui.androidstudy.animat.AnimationHome;
import com.example.wanghui.androidstudy.expandable.ExpandableTestActivity;
import com.example.wanghui.androidstudy.handler.HandlerMsgActivity;
import com.example.wanghui.androidstudy.interaction.MultiTouchActivity;
import com.example.wanghui.androidstudy.ipc.IPCActivity;
import com.example.wanghui.androidstudy.ipc.MessengerActivity;
import com.example.wanghui.androidstudy.media.AudioCaptureActivity;
import com.example.wanghui.androidstudy.media.DionisPlayerActivity;
import com.example.wanghui.androidstudy.media.VideoCaptureActivity;
import com.example.wanghui.androidstudy.keep.KeepActivity;
import com.example.wanghui.androidstudy.remoteviews.RemoteViewsUsage;
import com.example.wanghui.androidstudy.spannable.SpannableActivity;
import com.example.wanghui.androidstudy.testrxjava.TestRxJavaActivity;
import com.example.wanghui.androidstudy.ui.ChangeSystemUIActivity;
import com.example.wanghui.androidstudy.ui.ScrollActivity;
import com.example.wanghui.androidstudy.view.ViewActivity;

/**
 * Created by wanghui on 2016/4/5.
 */
public class SplashActivity extends FragmentActivity {
    private Button mButtonChangeSystemUI;
    private Button mButtonScaleUI;
    private Button mButtonTestRxJava;
    private Button mButtonMultiTouch;
    private Button mButtonMediaVideo;
    private Button mButtonDionisPlayer;
    private Button mButtonVideoList;
    private Button mButtonMediaAudio;
    private Button mButtonHandlder;
    private Button mButtonExpand;
    private Button mButtonSpanableString;
    private Button mButtonIPC;
    private Button mButtonAnimator;
    private SplashClickListener mSplashClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashClickListener = new SplashClickListener();
        initView();
        setClickListener(R.id.btn_remote_view);
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

        mButtonDionisPlayer = (Button) findViewById(R.id.btn_dionis_player);
        setClickListener(mButtonDionisPlayer);

        mButtonVideoList = (Button) findViewById(R.id.btn_keep_momery);
        setClickListener(mButtonVideoList);

        mButtonMediaAudio = (Button) findViewById(R.id.btn_media_audio);
        setClickListener(mButtonMediaAudio);

        mButtonHandlder = (Button) findViewById(R.id.btn_handler);
        setClickListener(mButtonHandlder);

        mButtonExpand = (Button) findViewById(R.id.btn_Expandable);
        setClickListener(mButtonExpand);

        mButtonSpanableString = (Button) findViewById(R.id.btn_spanable);
        setClickListener(mButtonSpanableString);

        mButtonIPC = (Button) findViewById(R.id.btn_ipc);
        setClickListener(mButtonIPC);

        mButtonAnimator = (Button) findViewById(R.id.btn_animation);
        setClickListener(mButtonAnimator);

        setClickListener(R.id.btn_view);
    }

    private void setClickListener(View view) {
        view.setOnClickListener(mSplashClickListener);
    }

    private void setClickListener(@IdRes int id){
        View view = findViewById(id);
        view.setOnClickListener(mSplashClickListener);
    }


    private class SplashClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_changeSystemUI:
                    Log.d("SplashActivity", v.getParent().toString());
//                    goChangeSystemUI();
                    showSnackBar();
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
                    break;
                case R.id.btn_dionis_player:
                    goDionisPlayer();
                    break;
                case R.id.btn_keep_momery:
                    goKeepMomery();
                    break;
                case R.id.btn_media_audio:
                    goMediaAudio();
                    break;
                case R.id.btn_handler:
                    goHandlerMsg();
                    break;
                case R.id.btn_Expandable:
                    goExpandActivity();
                    break;
                case R.id.btn_spanable:
                    goSpanableActivity();
                    break;
                case R.id.btn_ipc:
                    goIPCActivity();
                    break;
                case R.id.btn_view:
                    goViewActivity();
                    break;
                case R.id.btn_remote_view:
                    goRemoteViewsActivity();
                    break;
                case R.id.btn_animation:
                    goAnimationHome();
                    break;
            }
        }
    }

    private void showSnackBar() {
        Snackbar.make(mButtonAnimator, "被点击了", Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    private void goAnimationHome() {
        Intent intent = new Intent(this, AnimationHome.class);
        startActivity(intent);
    }

    private void goMediaAudio() {
        Intent intent = new Intent(this, AudioCaptureActivity.class);
        startActivity(intent);
    }

    private void goChangeSystemUI() {
        Intent intent = new Intent(this, ChangeSystemUIActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_enter);
    }

    private void goChangeScaleUI() {
        Intent intent = new Intent(this, ScrollActivity.class);
        startActivity(intent);
    }

    private void goTestRxJava() {
        Intent intent = new Intent(this, TestRxJavaActivity.class);
        startActivity(intent);
    }

    private void goMultiTouch() {
        Intent intent = new Intent(this, MultiTouchActivity.class);
        startActivity(intent);
    }

    private void goMediaVideo() {
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

    private void goDionisPlayer(){
        Intent intent = new Intent(this, DionisPlayerActivity.class);
        startActivity(intent);
    }

    private void goKeepMomery(){
        Intent intent = new Intent(this, KeepActivity.class);
        startActivity(intent);
    }

    private void goHandlerMsg(){
        Intent intent = new Intent(this, HandlerMsgActivity.class);
        startActivity(intent);
    }

    private void goExpandActivity(){
        Intent intent = new Intent(this, ExpandableTestActivity.class);
        startActivity(intent);
    }

    private void goSpanableActivity(){
        Intent intent = new Intent(this, SpannableActivity.class);
        startActivity(intent);
    }

    private void goIPCActivity(){
        Intent intent = new Intent(this, IPCActivity.class);
        startActivity(intent);
    }

    private void goViewActivity(){
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    private void goRemoteViewsActivity(){
        Intent intent = new Intent(this, RemoteViewsUsage.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("androidStudy", "wh----onRestart--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("androidStudy", "wh----onPause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("androidStudy", "wh----onStop--");
    }
}
