package com.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/5/13 14:48
 **/
@Slf4j
public class TestBigDeical {


     public static void main(String[] args) {
         TestBigDeical testBigDeical = new TestBigDeical();
         if(testBigDeical.handlePriceDifference(12,new BigDecimal("13"),new BigDecimal("12"))){
             log.info("异常记录");
         }
     }


    private boolean handlePriceDifference(Integer priceDifference, BigDecimal pricingPrice,BigDecimal inboundPrice) {
        if(priceDifference==null ||pricingPrice==null||inboundPrice==null){
            log.error("关键参数不能为空");
            return false;
        }
        BigDecimal divide = ((pricingPrice.subtract(inboundPrice)).divide(inboundPrice, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100));
        log.info("结果"+divide.toString());
        if(divide.compareTo(new BigDecimal(priceDifference.toString()))>=0){
            return true;
        }else{
            return false;
        }
    }
}