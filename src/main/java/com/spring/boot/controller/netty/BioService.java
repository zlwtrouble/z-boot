package com.spring.boot.controller.netty;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/5 10:58
 **/
@Slf4j
public class BioService{
                 public static void main(String[] args) {
                   int port=8080;
                   if(args !=null&&args.length>0){
                       try {
                           port = Integer.valueOf(args[0]);
                       }catch (NumberFormatException e){
                           log.info("【发生错误】",e);
                       }
                   }
                     ServerSocket server=null;

                     try {
                         server = new ServerSocket(port);
                         log.info("BIO服务器启动");
                         Socket socket=null;
                         while(true){
                             socket=server.accept();
                             new Thread(new TimeServerHandler(socket)).start();

                         }


                     } catch (IOException e) {
                         e.printStackTrace();
                     }finally {
                         if(server!=null){
                             try {
                                 server.close();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                             server=null;

                         }
                     }

                 }
}
