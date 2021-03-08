package com.zhaolw.zoo.boot.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @Description:
 * @Auther: zhaoliwei
 * @Date: 2018/9/6 14:48
 */
public class TestZERO {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }


    public static String wipeBigDecimalZero(BigDecimal number) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(6);
        nf.setGroupingUsed(false);
        return nf.format(number);
    }

    public static void main(String[] args) {
        BigDecimal one = new BigDecimal("0.004");
        BigDecimal money = one.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (money.compareTo(new BigDecimal("0.01")) < 0) {
            System.out.println("支付金额必须大于0.01");
        }
    }
}
