package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.common.utils.StringUtil;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/5/9 11:02
 **/
public class TestLenth {

    public static void main(String[] args) {

        String str = "1。";

        checkReason(str);

    }

    private static void checkReason(String str) {
        if (StringUtil.isNotBlank(str)) {
            int i = str.indexOf("。");
            if (i < 1) {
                System.out.println("取消原因格式错误");
            }
            String front = str.substring(0, i + 1);
            if (front.length() > 30) {
                System.out.println("非法访问");
            }
            String after = str.substring(i + 1);
            if (after.length() > 0 || front.contains("其他原因")) {
                if (after.length() > 500 || after.length() < 2) {
                    System.out.println("其他内容2-500字");
                }
            }

            System.out.println(front + "****后***:" + after);
        }
    }
}
