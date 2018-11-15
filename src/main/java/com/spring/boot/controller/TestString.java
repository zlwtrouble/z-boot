package com.spring.boot.controller;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/9/13 10:33
 **/
public class TestString {

//     public static void main(String[] args) {
//        String str1="";
//        String str2="aabb";
//        String str3="我来了";
//         String str4="我aa来了a";
//         String str5="我";
//         String str6="a";
//
//         System.out.println(str1.length());
//         System.out.println(str2.length());
//         System.out.println(str3.length());
//         System.out.println(str4.length());
//         System.out.println(Arrays.toString(str4.getBytes()));
//         System.out.println(Arrays.toString(str5.getBytes()));
//         System.out.println(Arrays.toString(str6.getBytes()));
//
//         System.out.println(str4.getBytes());
//      }

//    public static void main(String[] args) {
//// TODO Auto-generated method stub◇
//        String src = "a王大锤订单,wefw";
//        char[] t1 = null;
//        t1 = src.toCharArray();
//        int t0 = t1.length;
//        int count = 0;
//        for (int i = 0; i < t0; i++) {
//            count ++;
//            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                count ++;
//                ;
//            }
//        }
//        System.out.println(count);
//
//        String str6="▲▏";
//        System.out.println(Arrays.toString(str6.getBytes()));
//    }

     public static void main(String[] args) {
         String str1="";
        String str2="aabb";
       String str3="我来了";
         String str4="我aa来了a";
        String str5="我";
         String str6="我我我我我我我我我我我我我我C abaa";
         String str7=" a  b   ";
         System.out.println(""+TestString.stringCheck(str6));
         System.out.println(""+TestString.stringCheckLength(str7,1,3));
      }



    public final static boolean stringCheckLength(String str,int min,int max) {
        if(str.trim()!=""){
            str=str.trim();
            if(str.length()>=min && str.length()<=max){
                return true;
            }else {
                return  false;
            }
        }
        return false;
    }


    public final static boolean stringCheck(String str) {
        return match(str, "^(?!.*?[\\u3000-\\u303F\\u4DC0-\\u4DFF\\u2800-\\u28FF\\u3200-\\u32FF\\u3300-\\u33FF\\u2700-\\u27BF\\u2600-\\u26FF\\uFE10-\\uFE1F\\uFE30-\\uFE4F])[\\s\\u4e00-\\u9fbb\\u2E80-\\uFE4Fa-zA-Z0-9.`·]{1,20}$");
    }

    public final static boolean stringCheckLength(String str,int length) {
        String src = "a王大锤订单,wefw";
        char[] t1 = null;
        t1 = src.toCharArray();
        int t0 = t1.length;
        int count = 0;

        for (int i = 0; i < t0; i++) {
            count ++;
            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                count ++;
                ;
            }
        }

         return match(str, "^[a-zA-Z0-9\\u4e00-\\u9fa5-_]+$");
    }


    /**
     * 正则表达式匹配
     *
     * @param text 待匹配的文本
     * @param reg 正则表达式
     */
    private final static boolean match(String text, String reg) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(reg)){
            return false;
        }
        return Pattern.compile(reg).matcher(text).matches();
    }

    }
