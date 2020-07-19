package com.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

@Slf4j
public class ReadSelectedLine {

    static void readAppointedLineNumber(File sourceFile, int lineNumber) throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = null;
        int line = 1;
        if (lineNumber < 0 || lineNumber > getTotalLines(sourceFile)) {
            System.out.println("传入行数" + lineNumber + "有误，不在范围之内");
        } else {
            reader.setLineNumber(lineNumber);
            int i = reader.getLineNumber();
            while (reader.readLine() != null) {
                line++;
                if (i == line) {
                    s = reader.readLine();
                    break;
                }
            }
        }
        reader.close();
        in.close();
        log.info("结果" + s);
    }

    // 文件内容的总行数。
    static int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }

    public static void main(String[] args) throws IOException {

        // 读取文件
        File sourceFile = new File("d:/11.txt");
        // 获取文件的内容的总行数
        int totalNo = getTotalLines(sourceFile);
        System.out.println("There are " + totalNo + " lines in the text!");

        // 指定读取的行号
        int lineNumber = 2;

        // 读取指定的行
        readAppointedLineNumber(sourceFile, lineNumber);

    }


}
