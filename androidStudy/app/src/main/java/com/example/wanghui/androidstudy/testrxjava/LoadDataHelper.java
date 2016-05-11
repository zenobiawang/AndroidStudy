package com.example.wanghui.androidstudy.testrxjava;


import rx.Observable;

/**
 * Created by wanghui on 2016/5/10.
 */
public class LoadDataHelper {
    private static LoadDataHelper mHelper;
    public static LoadDataHelper getInstance(){
        if (mHelper == null){
            mHelper = new LoadDataHelper();
        }
        return mHelper;
    }


    public  void loadData(String url,RequestListener listener){

    }

    public Observable loadData(String url){
        return Observable.just(url);
    }

    public interface RequestListener{
        void onSuccess();
        void failed();
    }
}
