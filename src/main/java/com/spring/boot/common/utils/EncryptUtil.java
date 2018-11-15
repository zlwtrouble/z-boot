package com.spring.boot.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密类
 * EncryptUtil.java
 * @author but
 * @version v0.1
 * @date 2018年9月18日 上午10:43:14
 */

public class EncryptUtil {
    public static final String  MD5          = "MD5";
    public static final String  SHA256       = "SHA-256";
    public static final String  SHA512       = "SHA-512";
    public static final String  CHARSET_UTF8 = "UTF-8";
    public static final String  HMACSHA256   = "HMAC-SHA256";

    /**
     * md5
     * @param strText
     * @param charset
     * @return
     */
    public static String md5(String strText, String charset) {
        return encrypt(strText, MD5, charset);
    }

    /**
     * SHA256
     * @param strText
     * @param charset
     * @return
     */
    public static String sha256(String strText, String charset) {
        return encrypt(strText, SHA256, charset);
    }

    /**
     * SHA512
     * @param strText
     * @param charset
     * @return
     */
    public static String sha512(String strText, String charset) {
        return encrypt(strText, SHA512, charset);
    }

    /**
     * 加密
     * @param source 被加密数据
     * @param encryptType 加密方式
     * @param charset 编码格式
     * @return
     */
    public static String encrypt(String source, String encryptType, String charset) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        if (charset == null) {
            charset = CHARSET_UTF8;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(encryptType);
            byte[] byteBuffer = messageDigest.digest(source.getBytes(charset));
            StringBuffer strHexString = new StringBuffer();
            for (int i = 0; i < byteBuffer.length; i++) {
                strHexString.append(Integer.toHexString((byteBuffer[i] &0xFF) | 0x100).substring(
                        1, 3));
            }
            return strHexString.toString().toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String hmacsha256(String data, String key, String charset) throws Exception {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(charset), "HmacSHA256");
        hmacSHA256.init(secretKey);
        byte[] array = hmacSHA256.doFinal(data.getBytes(charset));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item &0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update("abcasd".getBytes());
        byte[] byteBuffer = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte item : byteBuffer) {
            sb.append(Integer.toHexString((item &0xFF) | 0x100).substring(1, 3));
        }
        System.out.println(sb);
    }


       /**
     * 加密
     * @param source 被加密数据
     * @param charset 编码格式
     * @return
     */
    public static String SHA512(String source, String charset) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        if (charset == null) {
            charset = CHARSET_UTF8;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA512);
            byte byteBuffer[] = messageDigest.digest(source.getBytes(charset));
            StringBuffer strHexString = new StringBuffer();
            for (int i = 0; i < byteBuffer.length; i++) {
                strHexString.append(Integer.toHexString((byteBuffer[i] & 0xFF) | 0x100).substring(
                    1, 3));
            }
            return strHexString.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密
     * @param source 被加密数据
     * @param charset 编码格式
     * @return
     */
    public static String paySHA512(String source, String charset) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(source)) {
            return null;
        }
        if (charset == null) {
            charset = CHARSET_UTF8;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA512);
            byte byteBuffer[] = messageDigest.digest(source.getBytes(charset));
            StringBuffer strHexString = new StringBuffer();
            for (int i = 0; i < byteBuffer.length; i++) {
                strHexString.append(Integer.toHexString((byteBuffer[i] & 0xFF) | 0x100).substring(
                        1, 3));
            }
            return strHexString.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
