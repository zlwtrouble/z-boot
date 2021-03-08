package com.zhaolw.zoo.boot.controller;

import java.math.BigDecimal;

/**
 * @Description: 删除特殊字符
 * @Auther: zhaoliwei
 * @Date: 2018/9/6 14:48
 */
public class TestByte {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }


    public static String wipeBigDecimalZero(BigDecimal money) {

        return null;
    }

    public static void main(String[] args) {
        String str = " 00900000001   ";
        str = str.replaceAll("\\u00A0+", "").trim();
        System.out.println(str.trim() + "结束");
    }
}
