package com.spring.boot.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;


/**
 * 签名类
 * SignUtil.java
 * @author but
 * @version v0.1 
 * @date 2018年9月18日 上午10:43:25
 */
public class SignUtil {

    /**
     * 验签
     * @param data 参与签名字段
     * @param key 密钥
     * @param removeSet 不参与签名字段
     * @return
     * @throws Exception
     */
    public static boolean verify(Map<String, String> data, String key, Set<String> removeSet)
            throws Exception {
        String sign = data.remove("sign");
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        return sign(data, key, removeSet).equals(sign);
    }

    /**
     * 签名
     * @param data 参与签名字段
     * @param key 密钥
     * @param removeSet 不参与签名字段
     * @return
     * @throws Exception
     */
    public static String sign(Map<String, String> data, String key,Set<String> removeSet) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if ("key".equals(key)) {
                continue;
            }
            if (removeSet != null && removeSet.contains(k)) {
                continue;
            }
            if (!StringUtils.isEmpty(data.get(k))){
                // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        if (!StringUtils.isEmpty(key)) {
            sb.append("key").append("=").append(key);
        }
        return EncryptUtil.sha512(sb.toString(), EncryptUtil.CHARSET_UTF8);
    }
}
