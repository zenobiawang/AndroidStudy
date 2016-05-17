package com.example.wanghui.androidstudy.testrxjava;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.wanghui.androidstudy.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        setContentView(R.layout.activity_test_rxa);
        setRxClick();
    }

    private void setRxClick() {
        RxView.clicks(findViewById(R.id.test_click))
                .throttleFirst(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(TestRxJavaActivity.this, "clicking", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
