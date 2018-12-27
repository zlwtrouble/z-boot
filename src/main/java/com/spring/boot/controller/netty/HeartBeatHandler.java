package com.spring.boot.controller.netty;

import com.spring.boot.common.enums.MessageType;
import com.spring.boot.entity.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/26 14:34
 **/
public class HeartBeatHandler extends SimpleChannelInboundHandler<Message> {

    //加入到在线列表，只有在线用户才可以实时推送
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ClientConnectionMap.addClientConnection(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        //如果是心跳包ping，则返回pong
        if(message != null && message.getType() == MessageType.HEARTBEAT_REQ.getValue()){
            Message response = buildMessage(MessageType.HEARTBEAT_RESP.getValue());
            ctx.writeAndFlush(response);
        }else{
            ctx.fireChannelRead(message);
        }
    }

    private Message buildMessage(byte result){
        Message msg = new Message();
        msg.setType(result);
        return msg;
    }

}
