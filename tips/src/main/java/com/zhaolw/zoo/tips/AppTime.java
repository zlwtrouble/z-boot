package com.zhaolw.zoo.tips;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/16 14:08
 **/
public class AppTime {

    /**
     * 上证
     */
    volatile static String sz = null;

    /**
     * 恒指科技
     */
    volatile static String hzkj = null;

    public static void main(String[] args) {

        JFrame f = new MySystemTray();
        f.setSize(500, 22);
        f.setLocation(1300, 5);
        f.setType(Window.Type.UTILITY);
        f.setLayout(null);
//        f.setState(Frame.ICONIFIED);
        Container con = f.getContentPane();
        con.setLayout(new GridLayout());
        JLabel l = new JLabel();
        double value = 0.5;
        f.setUndecorated(true);
        f.setBackground(new Color(0, 0, 0, 0));

        new Thread(new TaskSzApi()).start();
        new Thread(new TaskApi(l)).start();
        //字体
        l.setFont(new Font("Dialog", Font.BOLD, 20));
        //文字颜色
        l.setForeground(new Color(0, 255, 0));
        l.setBounds(0, 0, 0, 0);
        con.add(l);
        f.validate();
        f.setAlwaysOnTop(true);
        f.add(l);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    static class TaskSzApi implements Runnable {

        @Override
        public void run() {
            while (true) {
                boolean b = false;
                Date now = new Date();

                if (HttpUtil.isTimeRange(now, "9:25", "11:40")) {
                    b = true;
                }
                if (HttpUtil.isTimeRange(now, "13:00", "15:10")) {
                    b = true;
                }
                if (sz == null) {
                    b = true;
                }

                if (!b) {
                    System.out.println("sz" + b);
                    if (sz != null) {
                        sz = "";
                        hzkj = "";
                    }

                }

                try {
                    if (b) {
                        {
                            String url = String.format("http://web.juhe.cn:8080/finance/stock/hs?type=%s&key=%s", "0", "cd7ad78a1b39194922448d417704aed2");
                            String s = HttpUtil.netStock(null, url);
                            int i = s.indexOf("\"increPer\":");
                            int j = s.indexOf("\",\"", i);
                            sz = s.substring(i + 12, j);

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    sz = "";
                }

                try {
                    if (b) {
                        {
                            String url = String.format("http://qt.gtimg.cn/q=%s", "sh513180");
                            String s = HttpUtil.netStock("GBK", url);
                            List<String> strings = Arrays.asList(s.split("~"));
                            hzkj = strings.get(32);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    hzkj = "";
                }


                try {
                    Thread.sleep(6 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    static class TaskApi implements Runnable {
        private JLabel lab = new JLabel();

        public TaskApi(JLabel lab) {
            this.lab = lab;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        @Override
        public void run() {
            {
                Runtime rn = Runtime.getRuntime();
                Process p = null;
                try {
                    String command = "cmd.exe /c w32tm /resync";
                    p = rn.exec(command);
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
                    String line = null;
                    StringBuffer b = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        b.append(line + "\n");
                    }
                    System.out.println(b.toString());
                } catch (Exception e) {
                    System.out.println("Error win exec ");
                }
            }


            while (true) {
                try {
                    String msg = String.format("%s %s %s", sdf.format(new Date(System.currentTimeMillis() + 100L)), sz == null ? "" : sz, hzkj == null ? "" : hzkj);
                    lab.setText(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    lab.setText("error");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }


}
