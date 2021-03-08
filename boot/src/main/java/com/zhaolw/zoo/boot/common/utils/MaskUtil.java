package com.zhaolw.zoo.boot.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Ranj on 2017/7/18 0018.
 * <p>
 * 字符串掩码工具类
 */
public class MaskUtil {
    /**
     * 手机号掩码
     */
    private static String maskPhone(String tel) {
        if (tel == null || tel.length() == 0) return "";

        String str = wordMask(tel, 3, 4, "*");
        return str;


    }

    /**
     * 身份证号掩码
     */
    private static String maskCertId(String certId) {
        String str = "";
        if (certId == null || certId.length() == 0) return "";
        if (certId.length() == 18) {
            str = wordMask(certId, 5, 4, "*");
        }
        return str;

    }

    /**
     * 姓名掩码
     */
    private static String maskUserName(String userName) {
        if (userName == null || userName.length() == 0) return "";
        return wordMask(userName, 1, 0, "*");
    }

    /**
     * 字符串自定义掩码
     */
    private static String wordMask(String word, int startLength, int endLength, String pad) {
        if (StringUtils.isBlank(word)) {
            return "";
        }
        if (word.length() <= startLength + endLength) {
            return StringUtils.leftPad("", startLength + endLength, pad);
        }
        String startStr = "";
        String endStr = "";
        int padLength = 0;
        if (word.length() > startLength) {
            startStr = StringUtils.substring(word, 0, startLength);
        }
        if (word.length() > startLength + endLength) {
            endStr = StringUtils.substring(word, word.length() - endLength);
        }

        padLength = word.length() - startLength - endLength;

        return startStr + StringUtils.repeat(pad, padLength) + endStr;


    }

    public static void main(String[] args) {
        String str = MaskUtil.maskPhone("18523959726");
        String str1 = MaskUtil.maskCertId("500235199311089456");
        String str2 = MaskUtil.maskUserName("孔乙己");
        String str3 = MaskUtil.wordMask("2", 1, 1, "#");
        System.out.println(str);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }
}
