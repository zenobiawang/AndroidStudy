package com.example.wanghui.androidstudy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by wanghui on 2016/7/14.
 */
public class TryCatchTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
