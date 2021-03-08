package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.common.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/24 18:11
 **/
public class TestSign {

    public static void main(String[] args) {


        //签名
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("amount", "20");
        paraMap.put("cury", "CNY");
        paraMap.put("endTime", "20190124182657");
        paraMap.put("goodsDesc", "DD-20190124-448304：平垫 M6 碳钢 6.4mm 12mm 1.6mm 镀锌；");
        paraMap.put("mchId", "275203605394001920");
        paraMap.put("orderId", "DD-20190124-448304-P5");

        paraMap.put("respCode", "0000");
        paraMap.put("transChannel", "ALIPAY_PC_PAY");
        paraMap.put("transDate", "20190124");

        paraMap.put("transDesc", "DD-20190124-448304：平垫 M6 碳钢 6.4mm 12mm 1.6mm 镀锌；");
        paraMap.put("transInstu", "310000000001");
        paraMap.put("transTime", "162657");
        String key = "8DaKvaxjUToHuZxxgiKWAo3YlIDNslFaaNRFJoI9uFkpS8occmaHjtb1LEBkdkfeYn5kIUKuzPoRy6uhP2B2ihmopK17WbA7BnOih0H6EuCMhMadv62WdMCGwGWGgacg";
        String sign = null;
        try {
            sign = SignUtil.sign(paraMap, key);
            System.out.println(String.format("sign = %s", sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
