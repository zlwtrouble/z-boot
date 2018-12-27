package com.spring.boot.controller.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/13 15:52
 **/
@Slf4j
public class WebSocketServer {

    public void run(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wordGroup = new NioEventLoopGroup();
        try {


            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, wordGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("http-codec", new HttpServerCodec());
                    pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                    ch.pipeline().addLast("http-chunked", new WebSocketServerHandler());
                }
            });

            Channel ch = b.bind(port).sync().channel();

            log.info("Web socket server started at port" + port);

            ch.closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            wordGroup.shutdownGracefully();
        }
    }

     public static void main(String[] args) throws Exception {
      int port=8085;
      new WebSocketServer().run(port);
      }

}
