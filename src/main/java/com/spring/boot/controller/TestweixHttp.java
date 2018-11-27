package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.spring.boot.common.utils.DateUtil;
import com.spring.boot.common.utils.SignUtil;
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
public class TestweixHttp {


    final static String HHMMSS_PAY = "HHmmss";

    final static String YYYYMMDD_PAY = "yyyyMMdd";
     public static void main(String[] args) {
         TestweixHttp testHttp=new TestweixHttp();
         testHttp.getAlipay();
      }

    public String getAlipay() {

        //签名
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("mchId","252110195447447552");
        paraMap.put("transInstu","310000000001");
        paraMap.put("transChannel","ALIPAY_PC_PAY");
        paraMap.put("orderId","00000009");
        Date now = new Date();
        paraMap.put("transDate", DateUtil.format(now, TestweixHttp.YYYYMMDD_PAY));
        paraMap.put("transTime",DateUtil.format(now, TestweixHttp.HHMMSS_PAY));

        paraMap.put("amount","1");
        paraMap.put("backUrl","http：//192.168.173.75:9052/api/oms/payChannel/callBack");
        paraMap.put("transDesc","我是交易描述9");

        paraMap.put("endTime", "20181109153050");
        paraMap.put("sign","");
        String key ="tms";
        String sign = null;
        try {
            sign = SignUtil.sign(paraMap, key, null);
            System.out.println(String.format("sign = %s", sign));
            paraMap.put("sign", sign);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s=null;
        String url="http://192.168.173.144:7761/api/pay/placeOrder ";
        HttpPost post = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(30000).setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000).build();
        String resultStr = null;
        post = new HttpPost(url.trim());
        System.out.println("发送参数："+JSONObject.toJSONString(paraMap));
        try {
            post.setHeader("Content-type","application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setHeader("TransInstu", paraMap.get("transInstu"));
            post.setHeader("TransChannel", paraMap.get("transChannel"));

            // 构建消息实体
            StringEntity entity = new StringEntity(JSONObject.toJSONString(paraMap), Charset.forName("UTF-8"));
            post.setEntity(entity);


            HttpResponse responsePay = httpClient.execute(post);
            int statusCode=responsePay.getStatusLine().getStatusCode();
            System.out.println("状态码:"+statusCode);
            if(statusCode== HttpStatus.SC_OK) {
                //得到客户段响应的实体内容
                org.apache.http.HttpEntity entityPay = responsePay.getEntity();
                if (entityPay != null) {
                    s= EntityUtils.toString(entityPay,"UTF-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> result = JSON.parseObject(s, new TypeReference<HashMap<String, String>>() {
        });
        System.out.println("结果:"+JSONObject.toJSONString(result));

      return null;
    }
}
