package com.spring.boot.controller.netty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TimeClient {
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
        for (int j=1 ;j<999999;j++){


            int finalJ = j;
            threadPool.execute(new Runnable() {
               @Override
                public void run() {
                    sendByBio(finalSocket, finalJ);
                }
            });
        }

    }

    private static void sendByBio(Socket socket,int j) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
           // Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            socket = new Socket("192.168.158.173", 8055);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("QUERY TIME ORDER"+j);
            log.info("消息发送成功"+j);
            String resp = in.readLine();
            log.info("收到消息：" + resp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            try {
                socket.close();
                socket=null;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
