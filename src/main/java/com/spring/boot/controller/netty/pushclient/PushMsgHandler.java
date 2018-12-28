package com.spring.boot.controller.netty.pushclient;

import com.spring.boot.common.enums.MessageType;
import com.spring.boot.entity.Message;
import com.spring.boot.entity.PushMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/27 19:58
 **/
public class PushMsgHandler  extends SimpleChannelInboundHandler<Message> {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        PushMsg pushMsg = message.getMsg();
        System.out.println(pushMsg);
        if(message.getType() == MessageType.MSG_PUSH.getValue()){
           System.out.println("接收到消息"+msg.toString());
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

    }

}
