package com.zhaolw.zoo.boot.controller;

import java.math.BigDecimal;

/**
 * @Description:
 * @Auther: zhaoliwei
 * @Date: 2018/9/6 14:48
 */
public class TestZERO2 {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }


    public static String wipeBigDecimalZero(BigDecimal money) {
        ;
        System.out.println((money.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP)).toString());
        BigDecimal a = money.multiply(new BigDecimal("100"));
        System.out.println(a.setScale(0, BigDecimal.ROUND_HALF_UP));
        return "";
    }

    public static void main(String[] args) {
        BigDecimal num = new BigDecimal("1111111111111.20");
        TestZERO2.wipeBigDecimalZero(num);
    }
}
