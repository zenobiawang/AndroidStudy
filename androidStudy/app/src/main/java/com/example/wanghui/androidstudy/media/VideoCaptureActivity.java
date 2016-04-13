package com.example.wanghui.androidstudy.media;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.wanghui.androidstudy.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by wanghui on 2016/4/11.
 */
public class VideoCaptureActivity extends FragmentActivity implements SurfaceHolder.Callback{
    private Button mButtonRecord;
    private SurfaceView mPreView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private boolean mRecording = false;     //是否正在录像
    private boolean isCameraBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_video);
        mButtonRecord = (Button) findViewById(R.id.btn_record);
        mButtonRecord.setText("开始");

        mButtonRecord.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        mPreView = (SurfaceView) findViewById(R.id.sfv_video);
        mPreView.getHolder().addCallback(this);
        mPreView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        if (mCamera == null) {
            Camera.getNumberOfCameras();
            mCamera = Camera.open();
        }
        mCamera.setDisplayOrientation(90);
        mMediaRecorder = new MediaRecorder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceHolder = holder;
//        Camera.Parameters parameters = mCamera.getParameters();
//        Camera.Size size = getBestSupportedSize(parameters.getSupportedPreviewSizes(), width, height);
//        parameters.setPreviewSize(size.width, size.height);
//        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceHolder = holder;
        if (mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void onRecordClick(View view) throws IOException {
        updateRecordingState();
    }

    private void updateRecordingState() throws IOException {
        if (mRecording){
            mRecording = false;
            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mCamera.stopPreview();
            mCamera.lock();
            mButtonRecord.setText("开始");
        }else {
            initializeRecorder();
            mCamera.startPreview();
            mRecording = true;
            mCamera.unlock();
            mMediaRecorder.start();
            mButtonRecord.setText("结束");
        }
    }

    private void initializeRecorder() throws IOException {
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        File recordOutput = new File(Environment.getExternalStorageDirectory(), "recorded_video.mp4");
        if (recordOutput.exists()){
            recordOutput.delete();
        }
        CamcorderProfile cpHigh = CamcorderProfile.get(CamcorderProfile.QUALITY_720P);
        mMediaRecorder.setProfile(cpHigh);
        mMediaRecorder.setOutputFile(recordOutput.getAbsolutePath());
        mMediaRecorder.setPreviewDisplay(mPreView.getHolder().getSurface());
        if (isCameraBack){
            mMediaRecorder.setOrientationHint(90);   //后置摄像头转换90度
        }else {
            mMediaRecorder.setOrientationHint(270);  //前置需要转换270度，其他机型是否适用需要再调研
        }

        mMediaRecorder.setMaxDuration(10000);
//        mMediaRecorder.setMaxFileSize(10000000);       //最大文件数
        mMediaRecorder.prepare();


    }

    public void onTransformClick(View view) throws IOException {
        if (mRecording){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if (isCameraBack){
                for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                    Camera.CameraInfo info = new Camera.CameraInfo();
                    Camera.getCameraInfo(i, info);
                    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {//这就是前置摄像头
                        mCamera.stopPreview();//停掉原来摄像头的预览
                        mCamera.release();//释放资源
                        mCamera = null;//取消原来摄像头
                        mCamera = Camera.open(i);//打开当前选中的摄像头
                        isCameraBack = false;
                    }

                }
            }else {
                mCamera.stopPreview();//停掉原来摄像头的预览
                mCamera.release();//释放资源
                mCamera = null;//取消原来摄像头
                mCamera = Camera.open();//打开当前选中的摄像头
                isCameraBack = true;
            }
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.startPreview();
            mCamera.setDisplayOrientation(90);
//            initializeRecorder();
        }
    }

    private Camera.Size getBestSupportedSize(List<Camera.Size> sizes, int width, int height){
        Camera.Size bestSize = sizes.get(0);
        int largestArea = bestSize.width * bestSize.height;
        for (Camera.Size size : sizes){
            int area = size.height * size.width;
            if (area > largestArea && size.height < height && size.width < width){
                bestSize = size;
                largestArea = area;
            }
        }
        return bestSize;
    }
}
