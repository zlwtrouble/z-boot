package com.zhaolw.zoo.boot.controller;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/8/24 12:05
 **/
public class Client implements Comparable {
    String id;

    public Client(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object arg0) {
        Client c = (Client) arg0;
        String s2 = c.id.toLowerCase();
        String s1 = this.id.toLowerCase();
        if (s1.charAt(0) > s2.charAt(0)) {
            return 1;
        } else if (s1.charAt(0) < s2.charAt(0)) {
            return -1;
        } else {
            if (s1.length() == s2.length())
                return s1.compareTo(s2);
            else if (s1.length() > s2.length())
                return 1;
            return 0;
        }
    }
}