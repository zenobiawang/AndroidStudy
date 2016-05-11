package com.example.wanghui.androidstudy.testrxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wanghui on 2016/5/10.
 */
public class RxJavaDemo1Acrivity extends FragmentActivity {
    private TextView mTvContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosome);
        mTvContent = (TextView) findViewById(R.id.tv_test);
        initData();
    }

    private void initData() {
        method1();
        method2();
    }

    private void method1(){
        LoadDataHelper.getInstance().loadData("aaa", new LoadDataHelper.RequestListener() {
            @Override
            public void onSuccess() {
                LoadDataHelper.getInstance().loadData("bbb", new LoadDataHelper.RequestListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void failed() {

                    }
                });
            }

            @Override
            public void failed() {

            }
        });
    }

    private void method2() {
        Observable.just("aaa").concatMap(new Func1() {
            @Override
            public Object call(Object o) {
                return null;
            }
        }).concatMap(new Func1() {
            @Override
            public Object call(Object o) {
                return null;
            }
        }).toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }
}
