package com.spring.boot.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * httpclient utils
 * @author pmxin
 * @date 2018-0206 13:58:00
 */
public class HttpClientUtils {

    /**
     * post方法
     *
     * @param url
     * @param params
     *            以map形式封装的params
     * @return
     */
    public static String post(String url, Map<String, String> params, String charset) {
        HttpPost httpPost = new HttpPost(url);
        try {
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                Set<String> keySet = params.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()) {
					String key = iterator.next();
					String value = params.get(key);
					paramList.add(new BasicNameValuePair(key, value));
				}
                /*for (Map.Entry<String, String> entry : params.entrySet()) {
                	String key =entry.getKey();
                	String value = entry.getValue();

                }*/
                httpPost.setEntity(new UrlEncodedFormEntity(paramList,charset));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
        return executePost(httpPost,charset);
    }

    /**
     * post请求
     *
     * @param url
     * @param paramStr
     * @return
     */
    public static String post(String url, String paramStr,String charset){
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(paramStr, charset));
        } catch (Exception e) {
           return null;
        }

        return executePost(httpPost,charset);
    }


    public static String get(String url,String charset) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.10秒超时
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60*1000).setConnectTimeout(60*1000).build();
            httpget.setConfig(requestConfig);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                } else {
                    // 获取响应实体
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        return EntityUtils.toString(entity,charset);
                    }
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
        return null;
    }


    private static String executePost(HttpPost httpPost,String charset){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            //请求基本参数设置 10秒超时
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10*1000)
                .setConnectTimeout(10*1000).build();
            httpPost.setConfig(requestConfig);

            //执行post
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            try {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                } else {
                    // 获取响应实体
                    HttpEntity entity = httpResponse.getEntity();
                    return EntityUtils.toString(entity,charset);
                }
            } catch (Exception e) {
            } finally {
                httpResponse.close();
            }

        } catch (Exception e) {
            return null;
        } finally {
            try {
                //关闭连接，释放资源
                client.close();
            } catch (IOException e) {
            }
        }
        return null;
    }


     public static void main(String[] args) {
         String a1 = HttpClientUtils.post("www.baidu.com", "a1", "2");
        System.out.println("结果"+a1);
     }
}

