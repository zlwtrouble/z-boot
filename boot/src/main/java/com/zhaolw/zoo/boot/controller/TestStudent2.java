package com.zhaolw.zoo.boot.controller;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class TestStudent2 {


    public static void main(String[] args) {
        List<List<Long>> result = new ArrayList<>();
        List<Long> temp = new ArrayList<>(250000000);
        result.add(temp);
        OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        for (; ; ) {
            try {
                long available = osmb.getFreePhysicalMemorySize() / 1024 / 1024;
                System.out.println(available + "MB");

                System.out.println(result.size());
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
