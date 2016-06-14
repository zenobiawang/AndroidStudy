package com.example.wanghui.androidstudy.ipc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wanghui on 2016/6/14.
 */
public class TcpClientActivity extends Activity {
    private static final int MSG_CONNECT_SUCCESS = 1;
    private static final int MSG_RECEIVE_NEW = 2;
    private TextView mTvContent;
    private EditText mEtReply;
    private Button mBtnSend;
    private PrintWriter mPrintWriter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_CONNECT_SUCCESS:
                    mBtnSend.setClickable(true);
                    break;
                case MSG_RECEIVE_NEW:
                    String content = (String) msg.obj;
                    mTvContent.setText(mTvContent.getText() + "\n" + content);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);
        initView();
        Intent intent = new Intent(this, TcpServerService.class);
        startService(intent);
        new Thread(){
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    private void initView() {
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mEtReply = (EditText) findViewById(R.id.et_reply_content);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setClickable(false);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply = mEtReply.getText().toString().trim();
                if (reply != null){
                    mPrintWriter.println(reply);
                    mEtReply.setText("");
                    mTvContent.setText(mTvContent.getText() + "\n" + reply);
                }
            }
        });
    }

    private void connectTCPServer(){
        Socket socket = null;
        while (socket == null){
            try {
                socket = new Socket("localhost", 8345);
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MSG_CONNECT_SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TcpClientActivity.this.isFinishing()){
                String content = br.readLine();
                if (content != null){
                    mHandler.obtainMessage(MSG_RECEIVE_NEW, content).sendToTarget();
                }
            }
            br.close();
            mPrintWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
