package com.example.wanghui.androidstudy.interaction;

import android.app.Activity;
import android.os.Bundle;

import com.example.wanghui.androidstudy.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wanghui on 2016/9/18.
 */
public class PartLoadTestActivity extends Activity {
    private PartLoadView mPartLoadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setData();
    }

    private void initView() {
        setContentView(R.layout.activity_part_load);
        mPartLoadView = (PartLoadView) findViewById(R.id.part_view);
    }

    private void setData(){
        try {
            File file = new File("world.jpg");
            InputStream ins = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            ins.read(data);
            ins.close();
            mPartLoadView.setInputStream(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
