package com.example.wanghui.androidstudy.testrxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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

    private List<Object> getData(){
        return new ArrayList<>();
    }

    private void method2() {
       Observable.create(new Observable.OnSubscribe<Object>() {
           @Override
           public void call(Subscriber<? super Object> subscriber) {
               subscriber.onStart();
               List l = getData();
               subscriber.onNext(l);
               subscriber.onCompleted();
           }
       }).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Action1<Object>() {
                   @Override
                   public void call(Object o) {

                   }
               });

    }
}
