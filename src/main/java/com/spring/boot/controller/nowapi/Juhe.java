package com.spring.boot.controller.nowapi;

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
        f.setSize(120, 22);
        f.setLocation(900, 0);

        f.setLayout(null);
        Container con=f.getContentPane();
        con.setLayout(new GridLayout());
        JLabel l = new JLabel();
        double value = 0.5;
        f.setUndecorated(true);

        f.setBackground(new Color(0,0,0,0));


        new Thread(new com.spring.boot.controller.NewApi(l)).start();
        //文字颜色
        l.setForeground(Color.GRAY);
        l.setBounds(0, 0, 0, 0);
        con.add(l);
        f.validate();
        f.setAlwaysOnTop(true);
        f.add(l);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        if (com.sun.awt.AWTUtilities.isWindowOpaque(f)) {
//            com.sun.awt.AWTUtilities.setWindowOpacity(f, (float)(1 - value));
//        } else {
//            JOptionPane.showMessageDialog(f, "系统不支持 JDK版本过低或 JRE 系统库缺损");
//        }

        f.setVisible(true);


    }
}
