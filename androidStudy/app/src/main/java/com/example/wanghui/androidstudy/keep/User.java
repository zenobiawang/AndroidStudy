package com.example.wanghui.androidstudy.keep;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanghui on 2016/6/8.
 */
public class User implements Parcelable {
    protected User(Parcel in) {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
