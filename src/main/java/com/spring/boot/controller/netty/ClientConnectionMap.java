package com.spring.boot.controller.netty;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/27 17:03
 **/
@Slf4j
public class ClientConnectionMap {

    //保存一个gateway上所有的客户端连接
    public static ConcurrentHashMap<Long, ClientConnection> allClientMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Long> userid2netidMap = new ConcurrentHashMap<>();

    public static ClientConnection getClientConnection(ChannelHandlerContext ctx) {
        Long netId = ctx.attr(ClientConnection.NETID).get();

        ClientConnection conn = allClientMap.get(netId);
        if(conn != null)
            return conn;
        else {
            log.error("ClientConenction not found in allClientMap, netId: {}", netId);
        }
        return null;
    }

    public static ClientConnection getClientConnection(long netId) {
        ClientConnection conn = allClientMap.get(netId);
        if(conn != null)
            return conn;
        else {
            log.error("ClientConenction not found in allClientMap, netId: {}", netId);
        }
        return null;
    }

    public static void addClientConnection(ChannelHandlerContext c) {
        //fixme 之后重复登录需要踢掉原来的连接
        ClientConnection conn = new ClientConnection(c);

        if(ClientConnectionMap.allClientMap.putIfAbsent(conn.getNetId(), conn) != null) {
            log.error("Duplicated netid");
        }
    }

    public static void removeClientConnection(ChannelHandlerContext c) {
        ClientConnection conn = getClientConnection(c);
        long netid = conn.getNetId();
        String userid = conn.getUserId();
        if(ClientConnectionMap.allClientMap.remove(netid) != null) {
            unRegisterUserid(userid);
        } else {
            log.error("NetId: {} is not exist in allClientMap", netid);
        }

        log.info("Client disconnected, netid: {}, userid: {}", netid, userid);
    }

    public static void registerUserid(String userid, long netId) {
        if(userid2netidMap.putIfAbsent(userid, netId) == null) {
            ClientConnection conn = ClientConnectionMap.getClientConnection(netId);
            if(conn != null) {
                conn.setUserId(userid);
            } else {
                log.error("ClientConnection is null");
                return;
            }
        } else {
            log.error("userid: {} has registered in userid2netidMap", userid);
        }
    }

    protected static void unRegisterUserid(String userid) {
        if(ClientConnectionMap.userid2netidMap.remove(userid) == null) {
            log.error("UserId: {} is not exist in userid2netidMap", userid);
        }
    }

    public static Long userid2netid(String userid) {
        Long netid = userid2netidMap.get(userid);
        if(netid != null)
            return netid;
        else {
            log.error("User not login, userid: {}",userid);
        }
        return null;
    }
}
