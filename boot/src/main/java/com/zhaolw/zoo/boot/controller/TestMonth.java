package com.zhaolw.zoo.boot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/9/13 10:33
 **/
public class TestMonth {

    public static void main(String[] args) {
//        Calendar cal = new  Calendar.getInstance();
//        //或者用Calendar   cal   =   Calendar.getInstance();
//
//        /**设置date**/
//        String date = "2018-03";
//
//        SimpleDateFormat oSdf = new SimpleDateFormat("", Locale.ENGLISH);
//        oSdf.applyPattern("yyyy-MM");
//        try {
//            System.out.println(oSdf.parse(date));
//            cal.setTime(oSdf.parse(date));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        String dateStr = sdf.format(cal.getTime());
//        System.out.println("得到"+dateStr);
//
//        /**或者设置月份，注意月是从0开始计数的，所以用实际的月份-1才是你要的月份**/
//        //一月份: cal.set(   2009,   1-1,   1   );
//
//        /**如果要获取上个月的**/
//        //cal.set(Calendar.DAY_OF_MONTH, 1);
//        //日期减一,取得上月最后一天时间对象
//        //cal.add(Calendar.DAY_OF_MONTH, -1);
//        //输出上月最后一天日期
//        //System.out.println(cal.get(Calendar.DAY_OF_MONTH));
//        /**开始用的这个方法获取月的最大天数，总是得到是31天**/
//        //int num = cal.getMaximum(Calendar.DAY_OF_MONTH);
//        /**开始用的这个方法获取实际月的最大天数**/
//        int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//        System.out.println(maxNum);
//
//        Map<Integer, BigDecimal> resultMap=new HashMap<>();
//
//        for(int i=1;i<=maxNum;i++){
//            resultMap.put(i,new BigDecimal("5"));
//
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//            resultMap.put(i,resultMap.get(i).add(new BigDecimal("5.55")));
//
//        }
//
//
//        System.out.println("结果"+resultMap.toString());
//
//        String nowMonth = getLastMonth(0);
//        String lastMonth = getLastMonth(1);
//        System.out.println(nowMonth+"上月"+lastMonth);
//
//
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
//        LocalDateTime time = LocalDateTime.now();
//        String localTime = df.format(time);
//        LocalDateTime ldt = LocalDateTime.parse("2017-09",df);
//        System.out.println("LocalDateTime转成String类型的时间："+localTime);
//        System.out.println("String类型的时间转成LocalDateTime："+ldt);

        try {


            Properties prop = new Properties();
            String path = "E:\\proper";
            File file = new File(path);
            prop.load(new FileInputStream(file));
            System.out.println(prop.getProperty("customerId"));
        } catch (Exception e) {

        }

    }


    protected static String getLastMonth(int num) {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(num);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }


}
