package com.spring.boot.controller.netty.pushclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/27 20:00
 **/
public class PushClient {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    //换掉ip
    private String host = "192.168.158.173";
    private int port = 8001;

     public static void main(String[] args) {
         PushClient pushClient = new PushClient();
         try {
             pushClient.connect();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    public void connect() throws Exception {
        try{
            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline p = channel.pipeline();
                            p.addLast(new IdleStateHandler(20, 10, 0));
                            p.addLast(new ObjectEncoder());
                            p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                            p.addLast(new ReadTimeoutHandler(100));
                            p.addLast(new ConnectHandler());
                            p.addLast(new HeartBeatHandler());
                            p.addLast(new PushMsgHandler());
                        }
                    });
            System.out.println("开始连接");
            ChannelFuture future = bs.connect(new InetSocketAddress(host, port)).sync();
            future.channel().closeFuture().sync();//这一步会阻塞住
            System.out.println("关闭后");
        } finally {
            //断错重连
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Client 尝试重新连接-->>>>>>");
                    //等待InterVAl时间，重连
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        //发起重连
                        connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
