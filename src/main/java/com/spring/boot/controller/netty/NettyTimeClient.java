package com.spring.boot.controller.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/10 15:28
 **/
public class NettyTimeClient {

    public void connect(int port, String host) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();

            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel >() {

                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new NettyTimeClientHandler());
                }
            });
            //发起异步连接操作

            ChannelFuture f = b.connect(host, port).sync();
        } finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8090;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认值
            }
        }
        new NettyTimeClient().connect(port,"192.168.158.173");

    }
}
