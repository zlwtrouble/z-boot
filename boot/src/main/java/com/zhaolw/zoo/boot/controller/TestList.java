package com.zhaolw.zoo.boot.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: zhaoliwei
 * @Date: 2018/9/6 14:48
 */
@Slf4j
public class TestList {
//     public static void main(String[] args) {
//      Long ll=null;
//      if(ll == null  ){
//          System.out.println("bdyl");
//      }else {
//          System.out.println("空");
//      }
//      }


    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        System.out.println("" + list);

        List<Long> list2 = new ArrayList<>();
        list2.add(1L);

        List list3 = new ArrayList();
        list3.add(1L);
        System.out.println("" + list2.subList(0, 3));

        List intersection = TestList.retainAll(list, list3, list2, null);

        log.info("交集" + JSON.toJSONString(intersection));
    }

    /**
     * 求交集
     *
     * @param masterList 主数据列表
     * @param slaveList  从数据列表
     * @param <T>
     * @return
     */

//    public static <T> List<T> getIntersection(List<T> ... masterList, List<T> slaveList) {
//        List<T> list = new ArrayList<>();
//        return masterList.stream().filter(slaveList::contains).collect(Collectors.toList());
//        CollectionUtils.
//    }
    public static <T> List<T> retainAll(List<T>... lists) {
        List<T> result = null;
        boolean first = true;
        for (List<T> list : lists) {
            if (CollectionUtils.isEmpty(list)) {
                return new ArrayList<>();
            }
            if (first) {
                result = list;
                first = false;
            } else {
                result.retainAll(list);
            }
        }
        return result;
    }


//     public static void main(String[] args) {
//         List<Long> list = new ArrayList<>();
//         list.add(154564994156L);
//         list.add(254564984156L);
//         list.add(354564984156L);
//         list.add(454564984156L);
//         list.add(55456984156L);
//         list.add(654564984156L);
//         list.add(75456498455L);
//         list.add(854564984156L);
//         list.add(954564984156L);
//         list.add(1054564984156L);
//         list.add(5456498415L);
//         list.add(554564984156L);
//         list.add(454564984156L);
//         list.add(354564984156L);
//         list.add(25456498156L);
//         list.add(154564984156L);
//         list.add(5456498456L);
//         list.add(54564984156L);
//         list.add(54564984156L);
//         list.add(54564984156L);
//         list.add(54564984156L);
//         list.add(54564984156L);
//         list.add(32585555556L);
//         list.add(32585555556L);
//         list.add(32585555556L);
//         list.add(54564984156L);
//         list=list.stream().distinct().collect(Collectors.toList());
//         System.out.println(""+list);
//
//         List<Long> list2 = new ArrayList<>();
//         list2.add(54564984156L);
//         list2.add(54564984156L);
//         list2.add(54564984156L);
//         list2.add(54564984156L);
//         list2.add(54564984156L);
//         list2.add(32585555556L);
//         list2.add(32585555556L);
//         list2.add(32585555556L);
//         list2.add(54564984156L);
//         list2.add(154564994156L);
//         list2.add(254564984156L);
//         list2.add(354564984156L);
//         list2.add(454564984156L);
//         list2.add(55456984156L);
//
//         list2.add(554564984156L);
//         list2.add(454564984156L);
//         list2.add(354564984156L);
//         list2.add(654564984156L);
//         list2.add(75456498455L);
//         list2.add(854564984156L);
//         list2.add(954564984156L);
//         list2.add(1054564984156L);
//         list2.add(5456498415L);
//         list2.add(25456498156L);
//         list2.add(154564984156L);
//         list2.add(5456498456L);
//
//         list2=list2.stream().distinct().collect(Collectors.toList());
//         System.out.println(""+list2);
//
//      }
}
