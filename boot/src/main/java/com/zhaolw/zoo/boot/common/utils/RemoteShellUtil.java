package com.zhaolw.zoo.boot.common.utils;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/9/4 10:00
 **/
@Slf4j
public class RemoteShellUtil {

    private Connection conn;
    private String ip;
    private String username;
    private String password;
    private static final int TIME_OUT = 0;// 表示不超时

    private String charset = Charset.defaultCharset().toString();

    /**
     * 构造函数
     *
     * @param ip       远程ip
     * @param username 远程机器用户名
     * @param password 远程机器密码
     */
    public RemoteShellUtil(String ip, String username, String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }


    /**
     * 登录
     *
     * @return
     * @throws IOException
     */
    private boolean login() throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(username, password);
    }

    /**
     * 执行脚本
     *
     * @param shell
     * @return
     * @throws Exception
     */
    public int exec(String shell) throws Exception {
        int ret = -1;
        try {
            if (login()) {
                Session session = conn.openSession();
                session.execCommand(shell);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                ret = session.getExitStatus();
            } else {
                throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public int execCommand(String command) throws IOException {
        Session session = null;
        if (login()) {
            session = conn.openSession();
            session.execCommand(command);
            InputStream stdOut = new StreamGobbler(session.getStdout());
            String outStr = processStream(stdOut, charset);

            System.out.println("" + outStr);
        } else {
            log.error("登录远程机器失败" + ip);
        }

        RemoteShellUtil.close(session, conn);

        return 1;

    }

    private String processStream(InputStream in, String charset) {
        try {
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (in.read(buf) != -1) {
                sb.append(new String(buf, charset));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("流转String异常", e);
        }
        return null;
    }


    public static void close(Session session, Connection conn) {
        if (session != null) {
            session.close();
        }
        if (conn != null) {
            conn.close();
        }
    }


    public static void main(String[] args) {
        try {
            RemoteShellUtil executor = new RemoteShellUtil("192.168.173.75", "root", "hundsun@123");
            int exec = executor.execCommand("sh /usr/local/tomcat-report/autoreport.sh");
            System.out.println("" + exec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SshUtil(String hostname, String username, String password) {
        try {
            conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
