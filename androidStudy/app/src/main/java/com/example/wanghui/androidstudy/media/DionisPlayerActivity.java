package com.example.wanghui.androidstudy.media;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.wanghui.androidstudy.R;

import java.io.File;

/**
 * Created by wanghui on 2016/4/13.
 * 实现一个简易的播放器
 */
public class DionisPlayerActivity extends FragmentActivity {
    private VideoView mVideoView;
    private String mVideoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dionis_player);
        mVideoView = (VideoView) findViewById(R.id.videoview);

        File file = new File(Environment.getExternalStorageDirectory(), "recorded_video.mp4");
        mVideoUrl = file.getAbsolutePath();
        mVideoView.setVideoPath(mVideoUrl);
        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);

        mVideoView.start();
    }

    public void onPlayClick(View view){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }
}
