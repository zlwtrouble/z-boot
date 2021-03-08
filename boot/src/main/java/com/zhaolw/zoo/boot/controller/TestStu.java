package com.zhaolw.zoo.boot.controller;

import com.sun.management.OperatingSystemMXBean;
import sun.nio.ch.DirectBuffer;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TestStu {


    public static void clean(final ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            ((DirectBuffer) byteBuffer).cleaner().clean();
        }
    }

    public static void main(String[] args) {
        LinkedList<ByteBuffer> result = new LinkedList<>();
        OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        for (; ; ) {
            try {
                long available = osmb.getFreePhysicalMemorySize() / 1024 / 1024;
                System.out.println(simpleDateFormat.format(new Date()) + ":" + available + "MB");
                if (available > 2700 && result.size() < 15) {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
                    result.add(buffer);
                } else if (available < 1300) {
                    if (result.size() > 0) {
                        clean(result.removeLast());
                        System.gc();
                        System.out.println("removeLast" + osmb.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
                    }

                }
                System.out.println("remainingNumber:" + result.size());
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
