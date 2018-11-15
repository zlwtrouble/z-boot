package com.spring.boot.controller;

/**
 *
 */
public class TestPassword {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }



     public static void main(String[] args) {
         checkPasswordFormat("123456");
         checkPasswordFormat("12345");
         checkPasswordFormat("123456123456");
         checkPasswordFormat("1234561234567");
         checkPasswordFormat("123456123456a");
         checkPasswordFormat("asdfgdaduikj");
         checkPasswordFormat("AdfythAAAAAA");
         checkPasswordFormat("AAAAAAAAAAAA");
         checkPasswordFormat("123456.dddd");
         checkPasswordFormat("123456a");

      }

    private static void checkPasswordFormat(String password){
        String reg="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}";
        if(!password.matches(reg)){
            System.out.println("密码必须是6-12字母和数字组合；区分大小写；不含特殊符号。");
        }else{
            System.out.println(password);
        }
    }
}
