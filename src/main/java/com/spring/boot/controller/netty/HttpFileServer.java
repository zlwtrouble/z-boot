//package com.spring.boot.controller.netty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.HttpObjectAggregator;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpRequestEncoder;
//import io.netty.handler.stream.ChunkedWriteHandler;
//
///**
// * @author zhaoliwei
// * @description:
// * @date 2018/12/13 14:37
// **/
//public class HttpFileServer {
//    private static final String DEFAULT_URL="/src/com/phei/netty/";
//
//    public void run(final int port,final String url) throws Exception{
//        EventLoopGroup bossGroup=new NioEventLoopGroup();
//        EventLoopGroup worikGroup=new NioEventLoopGroup();
//        ServerBootstrap b=new ServerBootstrap();
//        b.group(bossGroup,worikGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
//            @Override
//            protected void initChannel(SocketChannel socketChannel) throws Exception {
//                socketChannel.pipeline().addLast("http-decoder",new HttpRequestDecoder());
//                socketChannel.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
//                socketChannel.pipeline().addLast("http-encoder",new HttpRequestEncoder());
//                socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
//                socketChannel.pipeline().addLast("fileServerHandler",new Httpf);
//            }
//        });
//
//    }
//
//}
