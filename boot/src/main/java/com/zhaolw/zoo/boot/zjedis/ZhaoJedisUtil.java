package com.zhaolw.zoo.boot.zjedis;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
public class ZhaoJedisUtil {


    private Socket socket;
    private String host;
    private Integer port;

    private ZhaoJedisUtil() {
    }

    private volatile static ZhaoJedisUtil connectRedisUtil;


    /**
     * 创建连接
     *
     * @param host     redis ip
     * @param port     redis 端口
     * @param password redis密码
     * @return
     */
    public static ZhaoJedisUtil getInstance(String host, Integer port, String password) {
        if (connectRedisUtil == null) {
            synchronized (ZhaoJedisUtil.class) {
                if (connectRedisUtil == null) {
                    connectRedisUtil = new ZhaoJedisUtil(host, port);
                    if (password != null && password.length() > 0) {
                        connectRedisUtil.auth(password);
                    }
                }

            }
        }

        return connectRedisUtil;
    }


    /**
     * 重置连接
     *
     * @param host     redis ip
     * @param port     redis 端口
     * @param password redis密码
     * @return
     */
    public static ZhaoJedisUtil againInstance(String host, Integer port, String password) {
        connectRedisUtil.close();
        connectRedisUtil = null;
        ZhaoJedisUtil.getInstance(host, port, password);
        return connectRedisUtil;
    }

    private ZhaoJedisUtil(String host, Integer port) {
        if (host == null) {
            host = "192.168.173.71";
        }
        if (port == null) {
            port = 6379;

        }
        this.host = host;
        this.port = port;

        connect();
    }


    public synchronized void connect() {
        if (!isConnected()) {
            try {
                socket = new Socket(host, port);
                log.info("socket connct info:{}", socket.isConnected());
            } catch (Exception e) {
                log.error("socket error", e);
            }
        }
    }

    /**
     * 关闭连接
     * close
     **/
    public synchronized void close() {
        if (socket != null) {
            try {
                socket.close();

            } catch (Exception e) {
                log.error("socket close error", e);
            }
            log.info("close info:{}", socket);
            socket = null;
        }
    }


    public boolean isConnected() {
        return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected()
                && !socket.isInputShutdown() && !socket.isOutputShutdown();
    }


    public String set(String key, String value) {
        try {
            this.sendCommand(socket.getOutputStream(), ZhaoJedisUtil.Command.SET, this.castValue(key),
                    this.castValue(value));
            return this.reply(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String auth(String password) {
        String result = null;
        try {
            sendCommand(socket.getOutputStream(), ZhaoJedisUtil.Command.AUTH, this.castValue(password));
            result = this.reply(socket.getInputStream());
            log.info("auth info:{}", result);
            if (!result.contains("ok")) {
                this.close();
                throw new RuntimeException("密码校验失败");
            }
            return result;
        } catch (IOException e) {
            log.error("send error", e);
        }
        return null;
    }

    public String get(String key) {
        try {
            this.sendCommand(socket.getOutputStream(), ZhaoJedisUtil.Command.GET, this.castValue(key));
            return this.reply(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static final String BULK_STRINGS = "$";
    private static final String ARRAY = "*";
    private static final String BLANK = "\r\n";

    public static enum Command {
        SET, GET, AUTH;
    }

    public void sendCommand(OutputStream outputStream, ZhaoJedisUtil.Command commond, byte[]... bs) {
        String protocolMessage = protocolMessage(commond, bs);
        try {
            outputStream.write(protocolMessage.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String reply(InputStream inputStream) {
        byte[] bs = null;
        try {
            bs = new byte[1024];
            inputStream.read(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(bs);
    }

    private String protocolMessage(ZhaoJedisUtil.Command commond, byte[]... bs) {
        StringBuffer message = new StringBuffer();
        message.append(ARRAY).append(bs.length + 1).append(BLANK);
        message.append(BULK_STRINGS).append(commond.name().length()).append(BLANK);
        message.append(commond).append(BLANK);
        for (int i = 0; i < bs.length; i++) {
            message.append(BULK_STRINGS).append(bs[i].length).append(BLANK);
            message.append(new String(bs[i])).append(BLANK);
        }

        return message.toString();
    }

    public byte[] castValue(String value) {
        if (value == null) {

            value = "";
        }
        return value.getBytes();
    }


}
