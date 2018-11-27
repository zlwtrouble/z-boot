package com.spring.boot.controller;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/16 14:08
 **/
public class Juhe {

    public static void main(String[] args) {

        JFrame f = new JFrame("LoL");
        f.setSize(180, 70);
        f.setLocation(0, 0);
        f.setLayout(null);
        Container con=f.getContentPane();
        con.setLayout(new GridLayout());
        JLabel l = new JLabel();
        new Thread(new NewApi(l)).start();
        //文字颜色
        l.setForeground(Color.red);
        l.setBounds(0, 0, 50, 50);
        con.add(l);

        f.setAlwaysOnTop(true);
        f.add(l);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);
    }
}
