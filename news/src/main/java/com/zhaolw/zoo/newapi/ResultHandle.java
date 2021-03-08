package com.zhaolw.zoo.newapi;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class ResultHandle {

    public static void main(String[] args) {
        String str = "var hq_str_sh600010=\"包钢股份,2.150,2.080,2.020,2.240,1.870,2.010,2.020,3248818886,6726403161.000,3187400,2.010,8420039,2.000,5339220,1.990,4275300,1.980,2656200,1.970,1442300,2.020,3561567,2.030,3351100,2.040,6385660,2.050,9219940,2.060,2021-03-08,14:15:21,00,\";\n" +
                "var hq_str_sh510310=\"HS300ETF,2.406,2.393,2.334,2.422,2.331,2.332,2.334,81148900,192588341.000,32200,2.332,92100,2.331,204100,2.330,123300,2.329,378500,2.328,190100,2.334,494700,2.336,325700,2.337,535500,2.338,995000,2.339,2021-03-08,14:15:20,00,\";\n" +
                "var hq_str_sz159949=\"创业板50,1.222,1.214,1.162,1.231,1.161,1.162,1.164,1583457457,1895570544.439,1124800,1.162,5280500,1.161,8367700,1.160,970200,1.159,3097500,1.158,1548800,1.164,945000,1.165,114100,1.166,3821000,1.167,493900,1.168,2021-03-08,14:15:21,00\";";

        List<String> list = fitList(str, "包钢股份");
        System.out.println(MessageFormat.format("【{0}:{1}】", list.get(0), list.get(3)));
    }

    public static List<String> fitList(String str, String name) {
        try {
            int start = str.indexOf(name);
            if (start > 1) {
                int end = str.indexOf(";", start);
                if (end > start) {
                    String substring = str.substring(start, end - 1);
                    System.out.println(substring);
                    List<String> strings = Arrays.asList(substring.split(","));
                    return strings;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fitMsg(String str, String name, int index) {
        try {
            List<String> list = ResultHandle.fitList(str, name);
            if (list == null || list.size() == 0) {
                return "";
            }
            return MessageFormat.format("【{0}:{1}】", name, list.get(index));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
