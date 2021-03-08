package com.zhaolw.zoo.newapi;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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


    public static String netStock() throws IOException {
        //股票接口
        InputStream in = null;
        try {
            URL u = new URL("http://hq.sinajs.cn/list=sh600010,sh510310,sz159949");
            in = u.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
            byte b[] = out.toByteArray();
            String str = new String(b, "GB18030");
            if (str.length() > 0) {
                return str;
            }

            return null;
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
            netStock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<String> result = new ArrayList<>();
                String s = netStock();
                result.add(ResultHandle.fitMsg(s, "包钢股份", 3));
                result.add(ResultHandle.fitMsg(s, "HS300ETF", 3));
                result.add(ResultHandle.fitMsg(s, "创业板50", 3));

                lab.setText(String.join(";", result));
                System.out.println("数据：" + lab.getText());


            } catch (Exception e) {
                e.printStackTrace();
                lab.setText("错误");
            }
            try {
                Thread.sleep(1200 * 60);
            } catch (InterruptedException e) {
            }
        }
    }
}
