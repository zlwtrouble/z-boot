package com.zhaolw.zoo.boot.common.utils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/16 11:08
 **/
public class MsgWebSocketClient extends WebSocketClient {
    public MsgWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        // TODO Auto-generated method stub
        System.out.println("握手...");
        for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext(); ) {
            String key = it.next();
            System.out.println(key + ":" + shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String paramString) {
        // TODO Auto-generated method stub
        System.out.println("接收到消息：" + paramString);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        // TODO Auto-generated method stub
        System.out.println("关闭...");
    }

    @Override
    public void onError(Exception e) {
        // TODO Auto-generated method stub
        System.out.println("异常" + e);

    }
}
