package com.zhaolw.zoo.boot.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;


/**
 * 支付服务用
 *
 * @author zhyu
 * @date 2017/11/20.
 */
public class SignUtil {
    private static final String ENCODING = "UTF-8";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String KEY_ALGORITHM = "RSA";
    public static final String RANDOM_KEY = "epayRandom";
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    /**
     * 签名
     *
     * @param dataMap       待签名map
     * @param privateKeyStr 私钥字符串
     * @return
     */
    public static String sign(Map<String, String> dataMap, String privateKeyStr) {
        try {
            if (privateKeyStr == null || "".equals(privateKeyStr)) {
                return null;
            }
            String content = map2Str(dataMap);
            if (content == null) {
                return null;
            }
            PrivateKey privateKey = restorePrivateKey(privateKeyStr);
            return sign(content, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 签名
     *
     * @param data       待签名字符串
     * @param privateKey 私钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static String sign(String data, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException,
            UnsupportedEncodingException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data.getBytes(ENCODING));
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 验证签名
     *
     * @param dataMap      待验签map
     * @param sign         签名
     * @param publicKeyStr 公钥字符串
     * @return
     */
    public static boolean verify(Map<String, String> dataMap, String sign,
                                 String publicKeyStr) {
        try {
            if (sign == null || "".equals(sign)) {
                return false;
            }
            if (publicKeyStr == null || "".equals(publicKeyStr)) {
                return false;
            }
            String content = map2Str(dataMap);
            if (content == null) {
                return false;
            }
            PublicKey publicKey = restorePublicKey(publicKeyStr);
            return verify(content, Base64.decodeBase64(sign), publicKey);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证签名
     *
     * @param data      待验签字符串
     * @param sign      签名
     * @param publicKey 公钥
     * @return
     */
    private static boolean verify(String data, byte[] sign,
                                  PublicKey publicKey) {
        if (data == null || sign == null || publicKey == null) {
            return false;
        }
        try {
            Signature signetcheck = Signature.getInstance(SIGNATURE_ALGORITHM);
            signetcheck.initVerify(publicKey);
            signetcheck.update(data.getBytes(ENCODING));
            return signetcheck.verify(sign);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取私钥
     *
     * @param privateKeyStr 私钥字符串
     * @return
     * @throws Exception
     */
    private static PrivateKey restorePrivateKey(String privateKeyStr)
            throws Exception {
        if (privateKeyStr == null || "".equals(privateKeyStr)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        byte[] encodedKey = privateKeyStr.getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    /**
     * 获取公钥
     *
     * @param publicKeyStr 公钥字符串
     * @return
     * @throws Exception
     */
    public static PublicKey restorePublicKey(String publicKeyStr)
            throws Exception {
        if (publicKeyStr == null || "".equals(publicKeyStr)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        byte[] encodedKey = publicKeyStr.getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    /**
     * 获取随机字符串
     *
     * @return
     */
    private static String generateNonceStr() {
        char[] nonceChars = new char[16];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS
                    .charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    /**
     * map转换成string，如果map中没有随机字符串，插入随机字符串
     *
     * @param dataMap
     * @return
     */
    private static String map2Str(Map<String, String> dataMap) {
        if (dataMap == null || dataMap.isEmpty()) {
            return null;
        }
        if (!dataMap.containsKey(RANDOM_KEY)) {
            dataMap.put(RANDOM_KEY, generateNonceStr());
        }
        StringBuffer sb = new StringBuffer();
        Map<String, String> treeMap = new TreeMap<String, String>(dataMap);
        for (Entry<String, String> entry : treeMap.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null || "".equals(entry.getValue())) {
                continue;
            }
            sb.append(entry.getKey()).append("=")
                    .append(entry.getValue().trim()).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
