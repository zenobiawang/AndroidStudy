package com.example.wanghui.androidstudy.keep;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghui on 2016/4/19.   文档跟想象的不一样
 */
public class KeepActivity extends FragmentActivity {
    private Map<String, DataEntity> mEntityMap = new HashMap<>();
    private Button mButton;
    private TextView mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep);
        mButton = (Button) findViewById(R.id.btn_read);
        mContent = (TextView) findViewById(R.id.tv_keep_content);
        final DataEntity entity = new DataEntity("hhhh", "123");
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "haha.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(entity);
            objectOutputStream.flush();
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < 10; i ++){
//            DataEntity dataEntity = new DataEntity("hhh" + i, "value" + i);
//            mEntityMap.put(i + "", dataEntity);
//        }
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "haha.txt"));
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(mEntityMap);
//            objectOutputStream.flush();
//            objectOutputStream.close();
//            fileOutputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();
                try {
                    FileInputStream inputStream = new FileInputStream(new File(Environment.getExternalStorageDirectory(), "haha.txt"));
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    DataEntity entity1 = (DataEntity) objectInputStream.readObject();
                    mContent.setText(entity.name + "-" + entity.value);
                    objectInputStream.close();
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                try {
//                    FileInputStream fileInputStream = new FileInputStream(new File(Environment.getExternalStorageDirectory(), "haha.txt"));
//                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//                    mEntityMap.clear();
//                    mEntityMap.putAll((Map<? extends String, ? extends DataEntity>) objectInputStream.readObject());
//                    for (DataEntity dataEntity : mEntityMap.values()){
//                        builder.append(dataEntity.name + "-" + dataEntity.value + "\n");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                mContent.setText(builder);
            }
        });
    }
}
