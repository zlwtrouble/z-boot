package com.zhaolw.zoo.boot.controller;

import com.zhaolw.zoo.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/7/2 15:46
 **/
@Slf4j
public class EsTool {

    public static ScheduledExecutorService scheduledExecutor = new ScheduledThreadPoolExecutor(3,
            new BasicThreadFactory.Builder().namingPattern("es-schedule-pool-%d").daemon(true).build());


    public static void main(String[] args) {
        EsTool esTool = new EsTool();
        for (int i = 0; i < 500000000; i++) {
            Student student = new Student();
            student.setId(i);
            esTool.scheduleUpdateOrAddSimpleProduct(student, 1, 5);
        }
        try {
            Thread.sleep(99999999999L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scheduleUpdateOrAddSimpleProduct(Student skuPendingDocumentVo, int count, int delayed) {
        EsTool.scheduledExecutor.schedule(() -> {
            if (count <= 6) {
                log.info("重试：{},pendingId{}", count, skuPendingDocumentVo.getId());
                int nextDelayed = 5 * count;
                log.info("下一次时间：{}", nextDelayed);
                this.scheduleUpdateOrAddSimpleProduct(skuPendingDocumentVo, count + 1, nextDelayed);
            } else {
                log.error("更新失败:pendingId:{}", skuPendingDocumentVo.getId());
            }
        }, delayed, TimeUnit.SECONDS);
    }
}
