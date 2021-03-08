package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/19 14:37
 **/
@Slf4j
public class TestStringBuild {
    public static void main(String[] args) {
        StringBuilder jointPurchaseGoodsMarkNote = new StringBuilder(" ");
        //String str[] = { "hello", "beijing", "world", "shenzhen" };
        String str[] = {" ", "", "  ", "22    "};

        for (int i = 0; i < str.length; i++) {
            if (StringUtil.isNotBlank(str[i])) {
                jointPurchaseGoodsMarkNote.append(str[i]).append(",");

            }
        }
        System.out.println(jointPurchaseGoodsMarkNote.toString());
        System.out.println(jointPurchaseGoodsMarkNote.length());
        if (jointPurchaseGoodsMarkNote.length() > 1) {

            log.info(jointPurchaseGoodsMarkNote.replace(jointPurchaseGoodsMarkNote.length() - 1, jointPurchaseGoodsMarkNote.length(), "。").toString());
        }
    }
}
