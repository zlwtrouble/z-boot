package com.spring.boot.controller.netty;

import com.spring.boot.common.enums.MessageType;
import com.spring.boot.entity.Message;
import com.spring.boot.entity.PushMsg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/26 14:33
 **/
public class PushServer {

        static int port=8001;

    public void bind() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bs = new ServerBootstrap();
        bs.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline p = channel.pipeline();
                        p.addLast(new ObjectEncoder());
                        p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                        //心跳超时
                        p.addLast(new ReadTimeoutHandler(100));
                        p.addLast(new ConnectHandler());
                        p.addLast(new HeartBeatHandler());
                    }
                });
        bs.bind(port).sync();
        System.out.println("com.liu.nettypushtest.server port start:"+port);
    }



    //消息推送
    public void push(){
        List<Channel> channels =new ArrayList<>();
        ConcurrentHashMap<Long, ClientConnection> allClientMap = ClientConnectionMap.allClientMap;
        for (ClientConnection clientConnection : allClientMap.values()) {
            channels.add(clientConnection.getCtx().channel());
        }
        System.out.println("push 消息 + " + channels.size());
        Message message = new Message();
        message.setType(MessageType.MSG_PUSH.getValue());
        PushMsg pushMsg = new PushMsg();
        pushMsg.setAuthor_name("中新社");
        pushMsg.setDate("2017-04-12 13:51");
        pushMsg.setThumbnail_pic_s("http:\\/\\/05.imgmini.eastday.com\\/mobile\\/20170412\\/20170412135121_ff0cae3d2601191a77afa948a8424142_1_mwpm_03200403.jpeg");
        pushMsg.setTitle("法国安娜思托保健品进军亚洲市场");
        pushMsg.setUrl("http:\\/\\/mini.eastday.com\\/mobile\\/170412135121788.html");
        message.setMsg(pushMsg);
        for (Channel channel : channels){
            channel.writeAndFlush(message);
        }
    }

    public static void main(String[] args) throws Exception{
        PushServer pushServer = new PushServer();
        pushServer.bind();
        while (true){
            pushServer.push();
            Thread.sleep(10000);
        }
    }

}
