package com.zhaolw.zoo.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestMap2 {


    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>(16);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        TestMap2 testMap2 = new TestMap2();
        executorService.execute(() -> {
            testMap2.add(map);
        });

        executorService.execute(() -> {
            testMap2.add(map);
        });

        executorService.execute(() -> {
            testMap2.del(map);
        });


        executorService.execute(() -> {
            testMap2.getSize(map);
        });
    }

    private void add(Map<String, String> map) {
        for (int i = 1; i < 10000000; i++) {
            map.put("" + i, "" + i);
        }
    }

    private void del(Map<String, String> map) {
        for (; ; ) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                map.remove(iterator.next());
            }
        }

    }

    private void getSize(Map<String, String> map) {
        for (; ; ) {
            log.info("集合大小" + map.size());
        }
    }
}
