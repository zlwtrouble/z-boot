//package com.spring.boot.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
//import javax.swing.*;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//        /**
//         *黄金数据调用示例代码 － 聚合数据
//         *在线接口文档：http://www.juhe.cn/docs/29
//         **/
//
//public class JuheDemo implements Runnable{
//
//            private JLabel lab=new JLabel();
//            public JuheDemo(JLabel lab){
//                this.lab=lab;
//            }
//            public static final String DEF_CHATSET = "UTF-8";
//            public static final int DEF_CONN_TIMEOUT = 30000;
//            public static final int DEF_READ_TIMEOUT = 30000;
//            public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
//
//            //配置您申请的KEY
//            public static final String APPKEY ="2fc77a08ab785559d1a3cdc4c2b3fb81";
//
//
//
//            //3.银行账户黄金
//            public static Map getRequest3(){
//                String result =null;
//                String url ="http://web.juhe.cn:8080/finance/gold/bankgold";
//                //请求接口地址
//                Map params = new HashMap();
//                //请求参数
//                params.put("key",APPKEY);
//                //APP Key
//
//                try {
//                    result =net(url, params, "GET");
//                    JSONObject object = JSONObject.fromObject(result);
//                    if(object.getInt("error_code")==0){
//                        JSONArray array= JSONArray.parseArray(object.get("result"));
//                        String str = array.get(0).toString();
//                        Map mapType = JSON.parseObject(str,Map.class);
//                        Map map = JSON.parseObject(mapType.get("1").toString(),Map.class);
//                        return map;
//                    }else{
//                        System.out.println(object.get("error_code")+":"+object.get("reason"));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//
//
//
//
//            /**
//      *
//      * @param strUrl 请求地址
//      * @param params 请求参数
//      * @param method 请求方法
//      * @return  网络请求字符串
//      * @throws Exception
//      */
//            public static String net(String strUrl, Map params,String method) throws Exception {
//                HttpURLConnection conn = null;
//                BufferedReader reader = null;
//                String rs = null;
//                try {
//                    StringBuffer sb = new StringBuffer();
//                    if(method==null || method.equals("GET")){
//                        strUrl = strUrl+"?"+urlencode(params);
//                    }
//                    URL url = new URL(strUrl);
//                    conn = (HttpURLConnection) url.openConnection();
//                    if(method==null || method.equals("GET")){
//                        conn.setRequestMethod("GET");
//                    }else{
//                        conn.setRequestMethod("POST");
//                        conn.setDoOutput(true);
//                    }
//                    conn.setRequestProperty("User-agent", userAgent);
//                    conn.setUseCaches(false);
//                    conn.setConnectTimeout(DEF_CONN_TIMEOUT);
//                    conn.setReadTimeout(DEF_READ_TIMEOUT);
//                    conn.setInstanceFollowRedirects(false);
//                    conn.connect();
//                    if (params!= null && method.equals("POST")) {
//                        try {
//                            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//                                out.writeBytes(urlencode(params));
//                        } catch (Exception e) {
//                            // TODO: handle exception
//                        }
//                    }
//                    InputStream is = conn.getInputStream();
//                    reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
//                    String strRead = null;
//                    while ((strRead = reader.readLine()) != null) {
//                        sb.append(strRead);
//                    }
//                    rs = sb.toString();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                    if (conn != null) {
//                        conn.disconnect();
//                    }
//                }
//                return rs;
//            }
//
//            //将map型转为请求参数型
//            public static String urlencode(Map<String,Object>data) {
//                StringBuilder sb = new StringBuilder();
//                for (Map.Entry i : data.entrySet()) {
//                    try {
//                        sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//                return sb.toString();
//            }
//
//            @Override
//            public void run() {
//                while (true){
//                    try {
//                        Map map = getRequest3();
//                        String time = map.get("time").toString();
//                        int i=time.indexOf(":");
//                        lab.setText(map.get("midpri").toString()+"  时间:"+time.substring(i-2,i+3));
//                        Thread.sleep(1000*60*7);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
