package com.spring.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.common.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/4/9 10:04
 **/
@Slf4j
public class TestSMS {
    private static final String SEND_MESSAGE_API = "/api/message/verifySign/sendMessage";

    private String messageKey="8DaKvaxjUToHuZxxgiKWAo3YlIDNslFaaNRFJoI9uFkpS8occmaHjtb1LEBkdkfeYn5kIUKuzPoRy6uhP2B2ihmopK17WbA7BnOih0H6EuCMhMadv62WdMCGwGWGgacg";
    private static final String REL = "rel";

    private static final String KEY_NAME = "key";

    private static final String DATA = "data";

     public static void main(String[] args) {
            TestSMS testSMS=new TestSMS();
         MessageBodyDto messageBodyDto=null;
         String json="{\"args\":[\"562025\",\"3\",\"400-8555-888\"],\"messageBodyDto\":{\"node\":\"wms_change_phone_password\",\"phone\":\"1398982772\"}}";
         messageBodyDto=JSONObject.parseObject(json,MessageBodyDto.class);
         testSMS.sendMessageApi(messageBodyDto);
      }


    /**
     *  发送消息
     * @param messageBodyDto
     * @return
     */
    public boolean sendMessageApi(MessageBodyDto messageBodyDto) {
        try {
            messageBodyDto.setMchId("275203605394001920");
            Map map = JSONObject.parseObject(JSONObject.toJSONString(messageBodyDto));
            String sign = sign(map, messageKey, KEY_NAME, EncryptUtil.SHA512, null);
            messageBodyDto.setSign(sign);
            map.put("sign",sign);
            String url = "http://192.168.13.144:7761"+SEND_MESSAGE_API;
            Object object = post(messageBodyDto,url);
            if(object != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("发送消息失败",e);
        }
        return false;
    }


    /**
     * post请求
     * @param object
     * @param url
     * @return
     * @throws Exception
     */
    JSONObject post(Object object,String url) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        String msg = JSONObject.toJSONString(object).toString();
        log.info("请求URL>>>>>>>>>>>>>>>>>>>{}",url);
        log.info("参数>>>>>>>>>>>>>>>>>>>>>>{}",msg);
        StringEntity entity = new StringEntity(JSONObject.toJSONString(object).toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            // 将响应内容转换为字符串
            String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            log.info("返回>>>>>>>>>>>>>>>>>>>>>>{}",result);
            JSONObject responVo = JSONObject.parseObject(result);
            if(responVo.getBoolean(REL) != null && responVo.getBoolean(REL)) {
                JSONObject data = responVo.getJSONObject(DATA);
                return data;
            }else {
                String respCode = responVo.getString("respCode");
                String respMsg = responVo.getString("respMsg");
                log.info("post message fail >>>>>>>>>>> respCode"+respCode+" respMsg:"+respMsg );
            }
        }
        return null;

    }


    /**
     * 替换模板中的内容，#{code}
     * @param template
     * @param args
     * @return
     * @throws Exception
     */
    public String replaceTemplate(String template,String[] args) throws Exception {
        String reg = "(#\\{\\w+\\})";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while(matcher.find()){
            args[index] = args[index].replace("\\", "\\\\").replace("$", "\\$");
            matcher.appendReplacement(sb, args[index]) ;
            index++;
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 签名
     * @param data 参与签名字段
     * @param key 密钥
     * @param keyName 密钥字段名
     * @param signType 签名类型
     * @param removeSet 不参与签名字段
     * @return
     * @throws Exception
     */
    public static String sign(Map<String, String> data, String key, String keyName,
                              String signType, Set<String> removeSet) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (keyName.equals(key)) {
                continue;
            }
            if (removeSet != null && removeSet.contains(k)) {
                continue;
            }
            // 参数值为空，则不参与签名
            if (!StringUtils.isEmpty(data.get(k))) {
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        if (!StringUtils.isEmpty(key)) {
            sb.append(keyName).append("=").append(key);
        }
        if (EncryptUtil.MD5.equals(signType)) {
            return EncryptUtil.md5(sb.toString(), EncryptUtil.CHARSET_UTF8);
        } else if (EncryptUtil.HMACSHA256.equals(signType)) {
            return EncryptUtil.hmacsha256(sb.toString(), key, EncryptUtil.CHARSET_UTF8);
        } else if (EncryptUtil.SHA512.equals(signType)) {
            return EncryptUtil.sha512(sb.toString(), EncryptUtil.CHARSET_UTF8);
        } else {
            throw new Exception(String.format("无效的签名类型: %s", signType));
        }
    }

}
