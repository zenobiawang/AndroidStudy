package com.example.wanghui.androidstudy.keep;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;

import com.example.wanghui.androidstudy.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghui on 2016/4/19.   文档跟想象的不一样
 */
public class KeepActivity extends FragmentActivity {
    private Map<String, DataEntity> mEntityMap = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        for (int i = 0; i < 10; i ++){
            DataEntity dataEntity = new DataEntity("hhh" + i, "value" + i);
            mEntityMap.put(i + "", dataEntity);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "haha.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (int i = 0; i < 10; i ++){
                objectOutputStream.write(i);
                objectOutputStream.writeObject(mEntityMap.get(i));
            }
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
