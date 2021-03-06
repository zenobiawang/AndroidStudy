package com.example.wanghui.androidstudy.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanghui on 2016/6/8.
 */
public class Book implements Parcelable {
    public String bookId;
    public String bookName;

    public Book(String bookName, String bookId) {
        this.bookName = bookName;
        this.bookId = bookId;
    }

    protected Book(Parcel in) {
        bookId = in.readString();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookId);
        dest.writeString(bookName);
    }
}
