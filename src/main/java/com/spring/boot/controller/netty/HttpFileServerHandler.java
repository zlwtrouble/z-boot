//package com.spring.boot.controller.netty;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.*;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.RandomAccessFile;
//
//import static io.netty.handler.codec.http.HttpResponseStatus.*;
//
///**
// * @author zhaoliwei
// * @description:
// * @date 2018/12/13 14:47
// **/
//public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
//        if(!request.getDecoderResult().isSuccess()){
//            sendError(ctx,BAD_REQUEST);
//            return;
//        }
//        if(request.getDecoderResult().isSuccess()){
//            sendError(ctx,METHOD_NOT_ALLOWED);
//            return;
//        }
//        final String uri=request.getUri();
//        final String path=sanitizeUri(uri);
//        if(path==null){
//            sendError(ctx,FORBIDDEN);
//            return;
//        }
//        File file=new File(path);
//        if(file.isHidden()||!file.exists()){
//            sendError(ctx,NOT_FOUND);
//            return;
//        }
//        if(!file.isFile()){
//            sendError(ctx,FORBIDDEN);
//            return;
//        }
////        RandomAccessFile randomAccessFile=null;
//        try {
//            randomAccessFile=new RandomAccessFile(file,"r");
//
//        }catch (FileNotFoundException fnfe){
//            sendError(ctx,NOT_FOUND);
//            return;
//        }
//        long fileLength=randomAccessFile.length();
//        HttpResponse response=new DefaultHttpResponse(HttpVersion.HTTP_1_1,OK);
//        setContentLength(response,file);
//        setContentTypeHeader(response,file);
//
//
//    }
//
//    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus badRequest) {
//
//
//    }
//}
