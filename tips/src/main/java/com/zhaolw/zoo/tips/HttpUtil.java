package com.zhaolw.zoo.tips;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/16 15:58
 **/
public class HttpUtil {


    public static Map net() throws IOException {
        InputStream in = null;
        try {
            URL u = new URL("http://web.juhe.cn:8080/finance/gold/shgold?key=2fc77a08ab785559d1a3cdc4c2b3fb81&format=json");
            in = u.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
            byte b[] = out.toByteArray();
            String str = new String(b, "utf-8");
            Map mapType = JSON.parseObject(str, Map.class);
            Map result = JSON.parseObject(mapType.get("result").toString(), Map.class);
            Map map = JSON.parseObject(result.get("1151").toString(), Map.class);
            //Map result = JSON.parseObject(result.get("result").toString(),Map.class);

            return map;
        } catch (Exception e) {

        } finally {
            if (in != null) {
                in.close();
            }
        }
        return null;
    }


    public static String netStock(String code, String url) {

        if (code == null) {
            code = "utf-8";
        }
        //股票接口
        InputStream in = null;
        try {
            URL u = new URL(url);
            in = u.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
            byte b[] = out.toByteArray();
            String str = new String(b, code);
            if (str.length() > 0) {
                return str;
            }

            return null;
        } catch (Exception e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static Boolean isTimeRange(Date now, String startStr, String endStr) {
        // "7:00"
        // "22:00"
        try {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date begin = df.parse(startStr);
            Date end = df.parse(endStr);
            Calendar nowTime = Calendar.getInstance();
            nowTime.setTime(df.parse(df.format(now)));
            Calendar beginTime = Calendar.getInstance();
            beginTime.setTime(begin);
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(end);
//            df.applyPattern("yyyy-MM-dd HH:mm:ss");
//            System.out.println(df.format(nowTime.getTime()));
//            System.out.println(df.format(beginTime.getTime()));
//            System.out.println(df.format(endTime.getTime()));
            if (nowTime.before(endTime) && nowTime.after(beginTime)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

//       System.out.println(isTimeRange(new Date(),"20:25","20:32"));

//            String s = netStock(null);
//            int i = s.indexOf("\"increPer\":");
//            int j = s.indexOf("\",\"", i);
//            System.out.println( s.substring(i+12,j));


        String url = String.format("http://qt.gtimg.cn/q=%s", "sh513180");
        String s = HttpUtil.netStock("GBK", url);
        System.out.println(s);
        List<String> strings = Arrays.asList(s.split("~"));
        System.out.println(strings.get(32));

    }


}
