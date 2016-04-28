package com.example.wanghui.androidstudy.testrxjava;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wanghui on 2016/4/8.
 * RxJava  Demo
 * http://www.tuicool.com/articles/BBNRRf
 */
public class TestRxJavaActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world!");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        observable.subscribe(subscriber);

        Observable<String> observable1 = Observable.just("hello world2");
        Action1 action1 = new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        observable1.subscribe(action1);

        Observable.just("hello world!").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "- ohh";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        query("hello, world!").flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> urls) {
                        return Observable.from(urls);
                    }
                })
                .subscribe(new Action1<String>(){
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                }) ;
    }

    Observable<List<String>> query(String text){
        return Observable.from(new ArrayList<List<String>>());
    }



}
