package com.example.wanghui.androidstudy.base;

/**
 * Created by wanghui on 2016/7/22.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public CrashHandler() {
        init();
    }

    private void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();
    }
}
