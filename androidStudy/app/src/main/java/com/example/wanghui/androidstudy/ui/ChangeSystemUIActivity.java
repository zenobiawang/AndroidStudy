package com.example.wanghui.androidstudy.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/4/5.
 * 应用程序体验需要对显示进行控制，移除各种系统修饰，例如状态栏和软件导航按钮
 */
public class ChangeSystemUIActivity extends FragmentActivity {
    private int mTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changesystemui);
    }

    public void onToggleClick(View view){
        int newVis;

        newVis = getTest3();

        view.setSystemUiVisibility(newVis);
    }


    private int getTest1() {   //夜间模式 调暗屏幕的导航控件，（并没有看见效果惹）
        return View.SYSTEM_UI_FLAG_LOW_PROFILE;
    }

    private int getTest2(){    //隐藏导航控件
        return View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    private int getTest3(){   //全屏UI模式
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

}
