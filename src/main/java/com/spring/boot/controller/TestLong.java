package com.spring.boot.controller;

import com.spring.boot.entity.User;

/**
 * @Description:
 * @Auther: zhaoliwei
 * @Date: 2018/9/6 14:48
 */
public class TestLong {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }



     public static void main(String[] args) {
         User user=new User();
         System.out.println(""+user);

         if(user==null){
             System.out.println("kong");
             return;
         }
         user.setName("");

         String str=user.getName().trim();
         str.length();
      }
}
