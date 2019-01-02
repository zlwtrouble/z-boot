package com.spring.boot.controller;

import java.math.BigDecimal;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/1 09:31
 **/
public class TestScale {
     public static void main(String[] args) {

         BigDecimal num = new BigDecimal("-1.11");
         num=new BigDecimal(num.stripTrailingZeros().toPlainString());
         int decimalScale = 0;
         System.out.println(""+num);
         System.out.println("小数位数"+num.scale());
         System.out.println("总长度"+num.precision());
         if (num.scale() > decimalScale ) {
             System.out.println("小数位数不符合：允许小数" + decimalScale + "位");
         }

     }
}
