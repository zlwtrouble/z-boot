package com.zhaolw.zoo.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestDateFormate {

//    public static void main(String[] args) {
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
//        Map<String,SimpleDateFormat> formatMap=new HashMap<>(16);
//        formatMap.put("yyyy-MM-dd HH:mm:ss",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        formatMap.put("yyyy-MM-dd",new SimpleDateFormat("yyyy-MM-dd"));
//        int i=0;
//        long start = System.currentTimeMillis();
//        for (;;){
//            if(i>100000){
//                break;
//            }
//            i++;
//            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
//            log.info("时间"+simpleDateFormat.format(new Date()));
//            simpleDateFormat.applyPattern("yyyy-MM-dd");
//            log.info("时间"+simpleDateFormat.format(new Date()));
//        }
//        log.info("耗时"+(System.currentTimeMillis()-start));
//
//    }


    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Map<String, SimpleDateFormat> formatMap = new HashMap<>(16);
        formatMap.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        formatMap.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
        int i = 0;
        long start = System.currentTimeMillis();
        for (; ; ) {
            if (i > 100000) {
                break;
            }
            i++;
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.info("时间" + simpleDateFormat.format(new Date()));
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            log.info("时间" + simpleDateFormat.format(new Date()));
        }
        log.info("耗时" + (System.currentTimeMillis() - start));

    }


//    public static void main(String[] args) {
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
//        Map<String,SimpleDateFormat> formatMap=new HashMap<>(16);
//        formatMap.put("yyyy-MM-dd HH:mm:ss",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        formatMap.put("yyyy-MM-dd",new SimpleDateFormat("yyyy-MM-dd"));
//        int i=0;
//        long start = System.currentTimeMillis();
//        for (;;){
//            if(i>100000){
//                break;
//            }
//            i++;
//            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
//            log.info("时间"+simpleDateFormat.format(new Date()));
//            simpleDateFormat.applyPattern("yyyy-MM-dd");
//            log.info("时间"+simpleDateFormat.format(new Date()));
//        }
//        log.info("耗时"+(System.currentTimeMillis()-start));
//
//    }
}
