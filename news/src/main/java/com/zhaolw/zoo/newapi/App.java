package com.zhaolw.zoo.newapi;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/16 14:08
 **/
public class App {

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


        new Thread(new NewApi(l)).start();
        //字体
        l.setFont(new java.awt.Font("Dialog", 1, 14));
        //文字颜色
        l.setForeground(new Color(0, 255, 0));
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
