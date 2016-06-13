package com.example.wanghui.androidstudy.ipc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/6/13.
 */
public class IPCActivity extends Activity {
    private View.OnClickListener mClickListener = new IPCClickListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
        initView(R.id.btn_messenger);
        initView(R.id.btn_aidl);
    }

    private void initView(int id) {
        View view = findViewById(id);
        view.setOnClickListener(mClickListener);
    }

    private class IPCClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_messenger:
                    goMessengerActivity();
                    break;
                case R.id.btn_aidl:
                    goBookManagerActivity();
                    break;
            }
        }
    }

    private void goMessengerActivity(){
        Intent intent = new Intent(this, MessengerActivity.class);
        startActivity(intent);
    }
    private void goBookManagerActivity(){
        Intent intent = new Intent(this, BookManagerActivity.class);
        startActivity(intent);
    }
}
