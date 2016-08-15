package com.example.wanghui.androidstudy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by wanghui on 2016/7/14.
 */
public class TryCatchTestActivity extends Activity {
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.sendEmptyMessage(1);
        try {
            throw new RuntimeException();
        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        finally {
            Log.d("androidStudy", "it is finally");
        }
    }
}
