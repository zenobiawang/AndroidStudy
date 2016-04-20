package com.example.wanghui.androidstudy.media;

import android.content.Context;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

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
    private TimerView mTimeView;
    private boolean mRecording = false;     //是否正在录像
    private boolean isCameraBack = true;
    private AudioManager audioManager;
    private boolean isFlashOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_video);
        mButtonRecord = (Button) findViewById(R.id.btn_record);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);   //关掉其他正在使用的音频
        mPreView = (SurfaceView) findViewById(R.id.sfv_video);
        mPreView.getHolder().addCallback(this);
        mPreView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mMediaRecorder = new MediaRecorder();
        mTimeView = (TimerView) findViewById(R.id.moving_view);
    }

    @Override
    protected void onResume() {
        mButtonRecord.setText("开始");
        audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("video", "----onPause");
        if (mRecording){
            doStop();
        }
        if (mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;
        try {
            if (mCamera == null){
                mCamera = Camera.open();
                mCamera.setDisplayOrientation(90);
            }
            mCamera.setPreviewDisplay(holder);
            Camera.Parameters parameters = mCamera.getParameters();
            List list = parameters.getSupportedFocusModes();
            if (list != null && list.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)){    //自动聚焦需要进行适配判断
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d("video", "-------surfaceCreated");
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("video", "-------surfaceChanged");
        mSurfaceHolder = holder;
//        Camera.Parameters parameters = mCamera.getParameters();
//        List list = parameters.getSupportedFocusModes();
//        if (list != null && list.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)){    //自动聚焦需要进行适配判断
//            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
//        }
//        mCamera.setParameters(parameters);
        if (mCamera == null){
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
        }
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("video", "-------surfaceDestroyed");
        mSurfaceHolder = holder;
    }

    public void onRecordClick(View view) throws IOException {
        updateRecordingState();
    }

    private void updateRecordingState() throws IOException {
        if (mRecording){
            doStop();
        }else {
            initializeRecorder();
            mTimeView.startMoving();
            mCamera.startPreview();
            mRecording = true;
            mCamera.unlock();
            mMediaRecorder.start();
            mButtonRecord.setText("结束");
        }
    }

    private void doStop(){
        mMediaRecorder.stop();
        mMediaRecorder.reset();
        mCamera.stopPreview();
        mCamera.lock();
        mTimeView.stopMoving();
        mButtonRecord.setText("开始");
        mRecording = false;
    }

    private void initializeRecorder() throws IOException {
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        File recordOutput = new File(Environment.getExternalStorageDirectory(), "recorded_video.mp4");
        if (recordOutput.exists()){
            recordOutput.delete();
        }

        // 方案1
//        CamcorderProfile camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
//        camcorderProfile.videoFrameWidth = 640;
//        camcorderProfile.videoFrameHeight = 480;
//        //  camcorderProfile.videoFrameRate = 15;
//        camcorderProfile.videoCodec = MediaRecorder.VideoEncoder.H264;
//        //  camcorderProfile.audioCodec = MediaRecorder.AudioEncoder.AAC;
//        camcorderProfile.fileFormat = MediaRecorder.OutputFormat.MPEG_4;
//        mMediaRecorder.setProfile(camcorderProfile);

        // 方案2
        // Set the recording profile.
        CamcorderProfile profile = null;                   //对拍摄质量的适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_CIF)){
                profile = CamcorderProfile.get(CamcorderProfile.QUALITY_CIF);
                Camera.Parameters parameters = mCamera.getParameters();
//                parameters.setJpegQuality(CameraProfile.);
                Log.d(":video...", "QUALITY_CIF");
            }else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_480P)) {
                profile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
                Log.d(":video...", "QUALITY_480P");
            } else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_QVGA)) {
                profile = CamcorderProfile.get(CamcorderProfile.QUALITY_QVGA);
                Log.d(":video...", "QUALITY_QVGA");
            } else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_LOW)) {
                profile = CamcorderProfile.get(CamcorderProfile.QUALITY_LOW);
                Log.d(":video...", "QUALITY_LOW");
            }
        }
//        profile = CamcorderProfile.get(CamcorderProfile.QUALITY_QVGA);
        if (profile != null) {
            mMediaRecorder.setProfile(profile);
        }


        mMediaRecorder.setOutputFile(recordOutput.getAbsolutePath());
        mMediaRecorder.setPreviewDisplay(mPreView.getHolder().getSurface());
        if (isCameraBack){
            mMediaRecorder.setOrientationHint(90);   //后置摄像头转换90度
        }else {
            mMediaRecorder.setOrientationHint(270);  //前置需要转换270度，其他机型是否适用需要再调研
        }

        mMediaRecorder.setMaxDuration(20000);
        mMediaRecorder.setMaxFileSize(3000000);       //最大文件数
        mMediaRecorder.prepare();


    }

    public void onTransformClick(View view){
        if (mRecording){
            Toast.makeText(this, "不能在录制过程中进行此操作", Toast.LENGTH_SHORT).show();
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
            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);
            } catch (IOException e) {
                Log.d("video", "-----------onTransformClick");
                e.printStackTrace();
            }
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

    public void onTurnOnClick(View view){   //打开闪光灯  适配
        if (mRecording){
            Toast.makeText(this, "不能在录制过程中进行此操作", Toast.LENGTH_SHORT).show();
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        List list = parameters.getSupportedFlashModes();
        if (list == null){
            return;
        }
        if (isFlashOn && list.contains(Camera.Parameters.FLASH_MODE_OFF)){
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        }else if ( list.contains(Camera.Parameters.FLASH_MODE_TORCH)){
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        }
        mCamera.setParameters(parameters);
    }

    @Override
    protected void onDestroy() {
        audioManager.abandonAudioFocus(null);
        mMediaRecorder = null;
        mCamera = null;
        super.onDestroy();
    }
}
