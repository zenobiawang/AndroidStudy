package com.example.wanghui.androidstudy.ipc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;

import com.example.wanghui.androidstudy.aidl.Book;
import com.example.wanghui.androidstudy.aidl.IBookManager;

import java.util.List;

/**
 * Created by wanghui on 2016/6/13.
 */
public class BookManagerActivity extends Activity {
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager manager = IBookManager.Stub.asInterface(service);
            try {
                List books = manager.getBookList();
                Log.d("wh", "the  list type " + books.getClass().getCanonicalName());
                manager.addBook(new Book("Android开发艺术探索", "3"));
                List newBooks = manager.getBookList();
                for (int i = 0; i < newBooks.size(); i ++){
                    Book book = (Book) newBooks.get(i);
                    Log.d("wh", book.bookName);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
