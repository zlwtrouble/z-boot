package com.zhaolw.zoo.boot.controller.nowapi;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/16 15:58
 **/
public class NewApi implements Runnable {
    private JLabel lab = new JLabel();

    public NewApi(JLabel lab) {
        this.lab = lab;
    }

    public static Map net() throws IOException {
        InputStream in = null;
        try {
            URL u = new URL("http://api.k780.com/?app=finance.gzgold&appkey=38143&sign=a309e3f83c78015a4bee13485d87c93d&format=json");
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

    public static void main(String[] args) {
        try {
            net();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Map map = net();
                String time = map.get("uptime").toString();
                int i = time.indexOf(":");
                lab.setText(map.get("last_price").toString().substring(0, 7) + "，" + time.substring(i - 2, i + 6));
                System.out.println("数据：" + time);

                Thread.sleep(1200 * 60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
