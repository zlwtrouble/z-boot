package com.spring.boot.controller.netty;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/5 15:59
 **/
@Slf4j
public class TimeClientHandleNio implements Runnable {
    private String host;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean stop;

    public TimeClientHandleNio(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            selector = Selector.open();

            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void run() {

        try {
            doConnet();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop) {
            try {
                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        //多路复用器关闭后，所有注册在上面的Channel和pope等资源都会被自动注册并关闭，所以不需要重复释放资源
        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //判断是否连接成功
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                } else {
                    //连接失败进程退出
                    System.exit(1);
                }

            }
            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    log.info("Now is:" + body);
                    this.stop = true;
                } else if (readBytes < 0) {
                    key.cancel();
                    ;
                    sc.close();
                } else {
                    //读到0字节，忽略
                    ;
                }
            }
        }
    }

    private void doConnet() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }


    }

    private void doWrite(SocketChannel socketChannel) throws IOException {

        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            log.info("Send order 2 server succeed.");
        }

    }
}
