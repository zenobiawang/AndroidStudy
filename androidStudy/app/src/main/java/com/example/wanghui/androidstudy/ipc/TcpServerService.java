package com.example.wanghui.androidstudy.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by wanghui on 2016/6/14.
 */
public class TcpServerService extends Service {
    private boolean mIsServiceDestoryed = false;
    private String[] mDefaultMessage = new String[]{
            "你好啊，哈哈"
            ,"你叫什么名字"
            ,"今天的天气不太好呀"
            ,"早上觉得好困的样子"};

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TcpServer implements Runnable{

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8345);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (!mIsServiceDestoryed){
                try {
                    final Socket client = serverSocket.accept();
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室");
        while (!mIsServiceDestoryed){
            String content = in.readLine();
            System.out.println("msg from client:" + content);
            if (content == null){  //客户端断开了链接
                break;
            }
            int i = new Random().nextInt(mDefaultMessage.length);
            String reply = mDefaultMessage[i];
            out.println(reply);
        }
        System.out.print("client quit.");
        out.close();
        in.close();
        client.close();
    }
}
