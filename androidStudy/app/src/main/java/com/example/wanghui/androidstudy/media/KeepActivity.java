package com.example.wanghui.androidstudy.media;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/4/19.
 */
public class KeepActivity extends FragmentActivity {
    private GridView mGridView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_video_list);
        mGridView = (GridView) findViewById(R.id.gv_video_list);
    }
}
