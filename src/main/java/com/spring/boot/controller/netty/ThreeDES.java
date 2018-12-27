package com.spring.boot.controller.netty;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/27 17:19
 **/
@Slf4j
public class ThreeDES {
    private static final String key = "qwejda!@#dasd235412faced";

    /**
     * 加密
     *
     * @param plainByte 明文
     *
     * @return 密文
     */
    public byte[] encrypt(byte[] plainByte) {

        byte[] cipherByte = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            cipherByte = cipher.doFinal(plainByte);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return cipherByte;
    }

    /**
     * 解密
     *
     * @param cipherByte 密文
     *
     * @return 明文
     */
    public byte[] decrypt(byte[] cipherByte) {

        byte[] decryptByte = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            decryptByte = cipher.doFinal(cipherByte);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return decryptByte;
    }



    public static void main(String[] args) throws Exception {
        byte[] b1={1,2,3};

        ThreeDES method = new ThreeDES();
        System.out.println("原始数据[" + b1.length + "]：");
        for(byte b : b1)
            System.out.print(b + " ");
        System.out.println();

        byte[] encode = method.encrypt(b1);

        System.out.println("加密数据[" + encode.length + "]：");
        for(byte b : encode)
            System.out.print(b + " ");
        System.out.println();

        byte[] decode = method.decrypt(encode);

        System.out.println("解密数据[" + decode.length+ "]：" );
        for(byte b : decode)
            System.out.print(b + " ");
        System.out.println();

    }

}
