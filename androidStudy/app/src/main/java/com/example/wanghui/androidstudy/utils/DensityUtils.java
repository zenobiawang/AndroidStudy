package com.example.wanghui.androidstudy.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by wanghui on 2016/6/30.
 */
public class DensityUtils {

    public static int getScreenWidth(Activity content) {
        DisplayMetrics dm = new DisplayMetrics();
        content.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;
        return mScreenWidth;
    }

    public static int getScreenHeight(Activity content) {
        DisplayMetrics dm = new DisplayMetrics();
        content.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenHeight = dm.heightPixels;
        return mScreenHeight;
    }
}
