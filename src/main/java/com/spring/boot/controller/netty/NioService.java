package com.spring.boot.controller.netty;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/5 10:58
 **/
@Slf4j
public class NioService {
                 public static void main(String[] args) {
                   int port=8055;
                   if(args !=null&&args.length>0){
                       try {
                           port = Integer.valueOf(args[0]);
                       }catch (NumberFormatException e){
                           log.info("【发生错误】",e);
                       }
                   }
                     MultiplexerTimeServer timeServer=new MultiplexerTimeServer(port);
                    new Thread(timeServer,"NIO-001").start();


                 }
}
