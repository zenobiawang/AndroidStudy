package com.example.wanghui.androidstudy.view;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.wanghui.androidstudy.R;


/**
 * Created by wanghui on 2016/8/3.
 * 画出一个引导页
 */
public class PorterduffDemoActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porterduff);
    }

    private void test(){
        SurfaceTexture texture = new SurfaceTexture(1);
        Surface surface = new Surface(texture);
        SurfaceHolder holder = new SurfaceHolder() {
            @Override
            public void addCallback(Callback callback) {

            }

            @Override
            public void removeCallback(Callback callback) {

            }

            @Override
            public boolean isCreating() {
                return false;
            }

            @Override
            public void setType(int type) {

            }

            @Override
            public void setFixedSize(int width, int height) {

            }

            @Override
            public void setSizeFromLayout() {

            }

            @Override
            public void setFormat(int format) {

            }

            @Override
            public void setKeepScreenOn(boolean screenOn) {

            }

            @Override
            public Canvas lockCanvas() {
                return null;
            }

            @Override
            public Canvas lockCanvas(Rect dirty) {
                return null;
            }

            @Override
            public void unlockCanvasAndPost(Canvas canvas) {

            }

            @Override
            public Rect getSurfaceFrame() {
                return null;
            }

            @Override
            public Surface getSurface() {
                return null;
            }
        };
        SurfaceView surfaceView = new SurfaceView(this);
        surfaceView.getHolder();
    }
}
