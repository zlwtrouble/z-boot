package com.zhaolw.zoo.newapi;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/16 14:08
 **/
public class AppTime {

    public static void main(String[] args) {

        JFrame f = new JFrame("N");
        f.setSize(500, 22);
        f.setLocation(1300, 5);

        f.setLayout(null);
        Container con = f.getContentPane();
        con.setLayout(new GridLayout());
        JLabel l = new JLabel();
        double value = 0.5;
        f.setUndecorated(true);
        f.setBackground(new Color(0, 0, 0, 0));

        new Thread(new TaskApi(l)).start();
        //字体
        l.setFont(new Font("Dialog", 1, 18));
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
                    lab.setText(String.join("", sdf.format(new Date(System.currentTimeMillis() + 100L))));

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
