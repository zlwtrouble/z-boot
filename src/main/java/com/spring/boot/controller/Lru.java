package com.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class Lru {

    public static void main(String[] args) {
        LinkedHashMap<String, String> cahche = new LinkedHashMap<>(5, 0.75F, true);

        cahche.put("1", "1");
        cahche.put("2", "2");
        cahche.put("3", "3");
        cahche.put("4", "4");
        cahche.put("5", "5");
        cahche.put("6", "6");

        String s = cahche.get("4");

        for (Map.Entry<String, String> stringStringEntry : cahche.entrySet()) {
            log.info(stringStringEntry.getKey());
        }
    }
}
