package com.zhaolw.zoo.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/5/13 14:48
 **/
@Slf4j
public class TestBigDeical {


//     public static void main(String[] args) {
//         TestBigDeical testBigDeical = new TestBigDeical();
//         if(testBigDeical.handlePriceDifference(12,new BigDecimal("13"),new BigDecimal("12"))){
//             log.info("异常记录");
//         }
//     }

//    public static void main(String[] args) {
//        BigDecimal bigDecimal = new BigDecimal("0");
//       System.out.println(""+bigDecimal);
//       System.out.println(""+bigDecimal.abs());
//    }
//
//
//    private boolean handlePriceDifference(Integer priceDifference, BigDecimal pricingPrice,BigDecimal inboundPrice) {
//        if(priceDifference==null ||pricingPrice==null||inboundPrice==null){
//            log.error("关键参数不能为空");
//            return false;
//        }
//        BigDecimal divide = ((pricingPrice.subtract(inboundPrice)).divide(inboundPrice, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100));
//        log.info("结果"+divide.toString());
//        if(divide.compareTo(new BigDecimal(priceDifference.toString()))>=0){
//            return true;
//        }else{
//            return false;
//        }
//    }


//    public static void main(String[] args) {
//        for (int i=1;i<15;i++){
//            BigDecimal size = new BigDecimal("" + i);
//            BigDecimal divisor = new BigDecimal("2" );
//            BigDecimal divide = size.divide(divisor, 0, BigDecimal.ROUND_HALF_UP);
//            int atLeastCount =divide.intValue();
//            System.out.println(i+"-->"+atLeastCount);
//        }
//
//    }

//    public static void main(String[] args) {
//        BigDecimal size = new BigDecimal("-2.45");
//        System.out.println(size.setScale(0, BigDecimal.ROUND_HALF_UP));
//
//        BigDecimal size2 = new BigDecimal("2.49");
//        System.out.println(size2.setScale(0, BigDecimal.ROUND_HALF_UP));
//
//    }

    public static void main(String[] args) {

        TestBigDeical testBigDeical = new TestBigDeical();
        testBigDeical.除不尽();
    }

    private void 小数位查看() {
        BigDecimal size = new BigDecimal("-21");
        System.out.println(size.scale());
        BigDecimal size2 = new BigDecimal("-21.0");
        System.out.println(size2.precision() - size2.scale());
    }

    private void 除不尽() {
        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("3");
        BigDecimal divide = one.divide(two, 6, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
    }
}
