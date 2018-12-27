package com.spring.boot.controller.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.websocketx.*;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/13 16:04
 **/
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private WebSocketServerHandshaker handshaker;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //传统的http接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof FullHttpRequest) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }


    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        //如果http解码失败，返回http异常
        if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        //构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:8080/websocket", null, false
        );
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse defaultFullHttpResponse) {


    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
    //判断是否是关闭链路的指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(),((CloseWebSocketFrame) frame).retain());
            return;
        }
        //判断是否是ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //本例仅支持文本消息，不支持二进制消息
        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException((String.format("%s frame types not supported",frame.getClass().getName())));
        }
        //返回应答消息
        String request=((TextWebSocketFrame)frame).text();


    }
}
