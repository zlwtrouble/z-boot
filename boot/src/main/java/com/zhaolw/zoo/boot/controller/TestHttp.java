package com.zhaolw.zoo.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zhaolw.zoo.boot.common.utils.DateUtil;
import com.zhaolw.zoo.boot.common.utils.SignUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhaoliwei
 * @description:
 * @date 2018/10/19 14:02
 **/
public class TestHttp {


    final static String HHMMSS_PAY = "HHmmss";

    final static String YYYYMMDD_PAY = "yyyyMMdd";

    public static void main(String[] args) {
        TestHttp testHttp = new TestHttp();
        testHttp.getAlipay();
    }

    public String getAlipay() {

        //签名
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("mchId", "252110195447447552");
        paraMap.put("transInstu", "310000000001");
        paraMap.put("transCode", "0101");
        paraMap.put("bizCode", "0101");
        paraMap.put("orderId", "DD-2019-4-25-01");
        Date now = new Date();
        paraMap.put("transDate", DateUtil.format(now, "yyyyMMdd"));
        paraMap.put("transTime", DateUtil.format(now, "HHmmss"));

        paraMap.put("amount", "1");
        paraMap.put("backUrl", "http://192.168.173.75:9052/api/oms/payChannel/callBack");
        paraMap.put("transDesc", "我是交易描述2019-4-25测");

        paraMap.put("endTime", DateUtil.format(new Date(System.currentTimeMillis() + 120 * 60 * 1000), "yyyyMMddHHmmss"));
        String key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCT1sa2laP0XD3Bb/TrLBMYBVv+iYKOfEeGIi62C8kWT2hRxkPZDQSUMzONv+suFwU+nP9hFK/cQHtRoXlTkI+6q5/k1f6EzY+I8POs0b9McK4VRcbK/bCNfF7np2o80T5wgF1DhFxEHE4sjzG2VWt46MsADiFqB/T6eFgT4JTstz3bwwxF0nEn6UAe7h753JLGtjJ6iyncmAZGAWixikjPvSdlQoBGlVlsrQemAPaVk4y1wkBJNCm+xnD66itR/l3CTO19qrYCuAxQrwvi6pX0LrGvvs9HKr5W4QTxGtWjUys6H2I3G300fCi3d9sPIuoEWpBA97lRQg3DICneDFKHAgMBAAECggEAcgbSNbmtP6h1qWEk0s6n23smvWhqXmPU9sweoyLr8l1E5O27Hwo/Yd8sAETqrB3tWaHWB5bhHUxO0WNuJNn3Qc9mACZdrSivcLwH9l/A4MB4RMgQ0DhjmH045gvmkQoC0trvFEhesdnzIcNAYJX50sQa7uv3McT5Wsa/iHVL6FcmKMAH37BfZDEw62TlEyCdEZ+ldcnMb/ZeJUY1kfwjRjhFDTPfs4Y0SCoTz6P4YBVIt9apS21RGsVkJglVy2MZi0rGiuB/NOitNApsHM38yF3p9AcFEanok/qJgYPqg0qJgvZKT3Zv8xlOnMVhK6vU/VxHuxDGFw8Vwy22mQc7gQKBgQD+7T3TVuLcWRQewtNx4xPmgsWCIoPjnH80cbtUPvKYhpSA/0sC9/9wPdaGkXELsOQWji8Rg2ifQf1Vh+rkirv3H318XLk/Fb0QVcG5JP30HSTNrLjtw1k8VoF76JqkP2KmhLoyd7w5KmkMa2a+zElst/QNBW2srwJn2ZLdNwUxlwKBgQCUdh2907popIfr6pHAB7QRzqnFM8pof2i/NsQmrccV/TbGA3fCJYiFdhLAZeYn2WnfEKAz7Ycq6O4k7s5vc+SuzZLCjq9+eY0S+CdSLbereIqzCdhQr+zYQBbICQUvtoalauhe5jPv3BUrDf8XWncdBsmhHcxJfVUobEeAcC4kkQKBgDhMGjA26uiwf2H/soRfGoD4djez+Z5lbC4fw7fqItcOOXN3bZBdRjr6XQrgya9EREq64agjdhJdl12xI22POc1sVeEF9R76ZQNbfOmvQqoLWZpWP4y6bFWH4RaFGsmU+es+OoR0rsuNEx/cBBe0s65fvXS2sy7Eg2p9wRI7WVlHAoGBAIyjE+uWXfjxG711uVpjMcbIsnSYaj7xq0WwWMKXTohbFf8gm9sz+Q8M2hierxfC0pM4SbrHYYtZmvcrri1QaSranRNnXYH/HZABuiYUHvD1wNZ4Fg3higkVNT+Lo/kd/JFJJBV6F2s6Xmcp3CPAVCQQIQNamgJoEzq20Ekwe9WxAoGBALKhWV/unC5C6NeaAqOITJbmfxgn+hx/EN8WXXz7QjjawEedQeU7wfYRU3YDYgSmMMtbol7CURcgpIdbvrZI4o146jb122EMZoty3aN8aaKKyokFGbo4F4+XGnesVMi676ro5ynG7evrCz36JX0bjl5+Qc8aNJ9yOUrXJBKRjjku";

        String sign = null;
        try {
            sign = SignUtil.sign(paraMap, key);
            System.out.println(String.format("sign = %s", sign));
            paraMap.put("sign", sign);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s = null;
        String url = "http://192.168.173.144:7761/api/pay/placeOrder ";
        HttpPost post = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(30000).setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000).build();
        String resultStr = null;
        post = new HttpPost(url.trim());
        System.out.println("发送参数：" + JSONObject.toJSONString(paraMap));
        try {
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setHeader("TransInstu", paraMap.get("transInstu"));
            // post.setHeader("TransChannel", paraMap.get("transChannel"));

            // 构建消息实体
            StringEntity entity = new StringEntity(JSONObject.toJSONString(paraMap), Charset.forName("UTF-8"));
            post.setEntity(entity);


            HttpResponse responsePay = httpClient.execute(post);
            int statusCode = responsePay.getStatusLine().getStatusCode();
            System.out.println("状态码:" + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                //得到客户段响应的实体内容
                org.apache.http.HttpEntity entityPay = responsePay.getEntity();
                if (entityPay != null) {
                    s = EntityUtils.toString(entityPay, "UTF-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> result = JSON.parseObject(s, new TypeReference<HashMap<String, String>>() {
        });
        System.out.println("结果:" + JSONObject.toJSONString(result));

        return null;
    }
}
