package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.common.enums.WebClientEnum;
import com.zhaolw.zoo.boot.common.utils.MsgWebSocketClient;

import java.net.URISyntaxException;

public class TestWebSocket {

    public static void main(String[] args) {
        try {
            WebClientEnum.CLIENT.initClient(new MsgWebSocketClient("wss://sshcdhpjkl.jin10.com:8080/socket.io/?EIO=3&transport=websocket&sid=ONZTcsXrCP6F2XHuCVZi"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
