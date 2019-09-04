package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/5/17 13:23
 **/
@Slf4j
public class testSort {


     public static void main(String[] args) {
         testSort testSort = new testSort();
         List<Integer> list=new ArrayList<>();
         list.add(5);
         list.add(5);
         list.add(2);
         list.add(3);
         list.add(null);
         list.add(0);
         list.add(7);
         list.add(null);
         log.info(JSON.toJSONString(list));
         testSort.sortOutboundDetailInfoListAsc(list);
         log.info(JSON.toJSONString(list));
     }

    private void sortOutboundDetailInfoListAsc(List<Integer> outboundDetailInfoList) {
        outboundDetailInfoList.sort((o1, o2) -> {
            if(o1==null){
                return 1;
            }
            if(o2==null){
                return -1;
            }
            //升序 null放最后  降序加负号
            return Integer.compare(o1.compareTo(o2),0 );
        });
    }
}
