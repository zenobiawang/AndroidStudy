package com.example.wanghui.androidstudy.testrxjava;

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

    public interface RequestListener{
        void onSuccess();
        void failed();
    }
}
