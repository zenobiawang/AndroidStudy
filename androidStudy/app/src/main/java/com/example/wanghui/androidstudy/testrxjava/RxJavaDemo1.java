package com.example.wanghui.androidstudy.testrxjava;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by wanghui on 2016/5/16.
 */
public class RxJavaDemo1 extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable.just("a", "b", "c", "d", "e")
                .map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return 1;
            }
        }).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("i = " + integer);
            }
        });
    }
}
