package com.zhaolw.zoo.boot.controller;

import java.math.BigDecimal;

/**
 * @Description:
 * @Auther: zhaoliwei
 * @Date: 2018/9/6 14:48
 */
public class TestLong2 {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }


    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("11111111111.11");
        BigDecimal b2 = new BigDecimal("11.111111");
        BigDecimal b3 = new BigDecimal("11.1111111111");
        BigDecimal b4 = new BigDecimal("11.111111");
        BigDecimal b5 = new BigDecimal("0");
        BigDecimal b6 = new BigDecimal("100000.000");
        System.out.println("b1:" + b1);
        System.out.println("b6:" + b6.stripTrailingZeros());
        System.out.println("b1:总长" + b1.precision());
        System.out.println("b2:总长" + b2.precision());
        System.out.println("b2:小数" + b1.scale());
        System.out.println("b5:小数" + b5.scale());
        System.out.println("b6:小数" + b6.scale());
        System.out.println("b1 2:" + checkDecimalPlace(b1, 2));
    }

    /**
     * 检查数量是否符合要求
     *
     * @param num
     * @param decimalScale
     * @return
     */
    protected static boolean checkDecimalPlace(BigDecimal num, Integer decimalScale) {
        if (num.scale() <= decimalScale && num.precision() <= (decimalScale + 10)) {
            return true;
        }
        return false;
    }


}
