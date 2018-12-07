package com.spring.boot.controller.netty;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TimeClientNio {
    static ExecutorService threadPool = new ThreadPoolExecutor(1330, 1350, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1300),
            new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build());






    public static void main(String[] args) {
        int port = 8055;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        Socket socket = null;
        try {
            socket = new Socket("192.168.158.173", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket finalSocket = socket;
        for(int i=0 ;i<1000;i++){
            try {
                 Thread.sleep(5000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j=1 ;j<999;j++){


                int finalJ = j;

            threadPool.execute(new Runnable() {
               @Override
                public void run() {
                    sendByBio(finalSocket, finalJ);
                }
            });
        }
        }

    }

    private static void sendByBio(Socket socket,int j) {


        try {
           // socket = new Socket("192.168.158.173", 8055);
            TimeClientHandleNio timeClientHandleNio = new TimeClientHandleNio("192.168.158.173", 8055);
            log.info("完成" + JSONObject.toJSONString(timeClientHandleNio));
        } catch (Exception e) {
            e.printStackTrace();
        }


        }

}
