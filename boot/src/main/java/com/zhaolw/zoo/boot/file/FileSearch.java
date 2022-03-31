package com.zhaolw.zoo.boot.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhaolw
 */
public class FileSearch {


    /**
     * 排除的目录
     */
    static List<String> excludeList = new ArrayList<>();


    /**
     * 包含的目录
     */
    static List<String> conList = new ArrayList<>();


    static int m = 1;

    static void search(File a, String x) throws IOException {
        Scanner scan = new Scanner(a, "utf-8");
        int k1 = 0;
        while (true) {
            if (!scan.hasNext()) {
                break;
            }
            String s1 = scan.nextLine();
            k1++;
            if (s1.contains(x)) {

                if (excludeList != null && excludeList.size() > 0) {
                    boolean tgFalg = false;
                    for (String tg : excludeList) {
                        if (a.getPath().contains(tg)) {
                            tgFalg = true;
                            break;
                        }
                    }
                    if (tgFalg) {
                        continue;
                    }
                }

                if (conList != null && conList.size() > 0) {
                    boolean tgcFalg = false;
                    for (String tg : conList) {
                        if (a.getPath().contains(tg)) {
                            tgcFalg = true;
                            break;
                        }
                    }
                    if (!tgcFalg) {
                        continue;
                    }
                }


                if (s1.length() > 100) {
                    s1 = s1.substring(0, 99) + "...";
                }
                String ss1 = m + ".文件:" + a.getPath() + " 第" + k1 + "行 \n  内容：" + s1.trim();
                System.out.println(ss1);
                m++;
            }
        }
    }

    static void f(File a, String s) {
        File[] ff = a.listFiles();
        if (ff == null) {
            return;
        }
        for (File it : ff) {
            if (it.isFile()) {
                try {
                    search(it, s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (it.isDirectory()) {
                f(it, s);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("开始位置");

        scs();
//        web();
        System.out.println("结束位置");
    }

    public static void scs() {
        //配置剔除的目录路径关键字
        excludeList.add("\\.idea\\");
        excludeList.add("\\criteria\\");
        excludeList.add("\\sentinel-dashboard\\");
        excludeList.add("\\scs-tx\\");
        excludeList.add("\\xxl-rpc-core\\");
        excludeList.add("\\xxl-mq-client\\");

        //配置路径
        f(new File("D:\\softidea\\scs-app-three"), "throw new");
        f(new File("D:\\softidea\\scs-app-three"), "BaseResultVo.error");
    }

    public static void web() {
        //配置只扫描的目录路径关键字
        conList.add("\\src\\");
        f(new File("D:\\softidea\\web\\xf"), "message.error");
        f(new File("D:\\softidea\\web\\xf"), "message:");

    }
}
