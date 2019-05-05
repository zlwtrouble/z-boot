package com.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.spring.boot.common.utils.DateUtil;
import com.spring.boot.controller.result.FlowChart;
import com.spring.boot.controller.result.Goods;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/11/22 11:06
 **/
@Slf4j
public class TestStreamSort {
    public static void main(String[] args) {
        List<FlowChart> flowChartList = new ArrayList<>();
        FlowChart flowChart = new FlowChart();
        flowChart.setId(1L);
        flowChart.setSortTime(DateUtil.parse("2019-3-15 12:55:33"));
        flowChart.setResidueNumber(new BigDecimal("15"));
        flowChartList.add(flowChart);

        FlowChart flowChart2 = new FlowChart();
        flowChart2.setId(2L);
        flowChart2.setSortTime(DateUtil.parse("2019-3-16 12:55:32"));
        flowChart2.setResidueNumber(new BigDecimal("15.30"));
        flowChartList.add(flowChart2);

        FlowChart flowChart3 = new FlowChart();
        flowChart3.setId(2L);
        flowChart3.setSortTime(DateUtil.parse("2019-3-14 12:55:32"));
        flowChart3.setResidueNumber(new BigDecimal("14.30"));
        flowChartList.add(flowChart3);

        FlowChart flowChart4 = new FlowChart();
        flowChart4.setId(4L);
        flowChart4.setResidueNumber(new BigDecimal("11.30"));
        flowChart4.setSortTime(DateUtil.parse("2019-3-11 12:55:32"));
        flowChartList.add(flowChart4);

        //集合排序
        flowChartList.sort((o1, o2) -> {
            if (o1 == null || o2 == null || o1.getSortTime() == null || o2.getSortTime() == null) {
                return 0;
            }
            if (o1.getSortTime().compareTo(o2.getSortTime()) > 0) {
                return 1;
            }
            if (o1.getSortTime().compareTo(o2.getSortTime()) == 0) {
                return 0;
            }
            return -1;
        });

        flowChartList.forEach(o -> {

        });

        Map<Long, Goods> map = new HashMap<>();
        Goods goods3 = new Goods();
        goods3.setId(2L);
        goods3.setReceiptGoodsAmount(new BigDecimal("32.33"));
        map.put(goods3.getId(), goods3);
        FlowChart childrenFlowChart = new FlowChart();
        String documentId = String.valueOf(252L);
        for (FlowChart dbFlowChart : flowChartList) {
            Goods purchaseBillGoodsVo = map.get(dbFlowChart.getId());
            if (purchaseBillGoodsVo == null) {
                continue;
            }
            BigDecimal execute = purchaseBillGoodsVo.getReceiptGoodsAmount();
            if (execute == null || BigDecimal.ZERO.compareTo(execute) == 0) {
                continue;
            }

            //分配数量
            BigDecimal nonExecute = allotNumber(documentId, dbFlowChart, execute);
            if (nonExecute != null) {
                purchaseBillGoodsVo.setReceiptGoodsAmount(nonExecute);
            }

        }

        log.info("MAP" + JSON.toJSONString(map) + "结果" + JSON.toJSONString(flowChartList));


    }

    private static BigDecimal allotNumber(String documentId, FlowChart dbFlowChart, BigDecimal execute) {
        BigDecimal nonExecute = null;
        String yz = String.valueOf(dbFlowChart.getResidueNumber());
        String yzx = String.valueOf(execute);
        String zxs = null;
        if (dbFlowChart.getResidueNumber().compareTo(execute) > 0) {
            zxs = String.valueOf(execute);
            dbFlowChart.setResidueNumber(dbFlowChart.getResidueNumber().subtract(execute));
            nonExecute = BigDecimal.ZERO;
            dbFlowChart.setNodeStatus(0);
        } else {
            zxs = String.valueOf(dbFlowChart.getResidueNumber());
            nonExecute = execute.subtract(dbFlowChart.getResidueNumber());
            dbFlowChart.setResidueNumber(BigDecimal.ZERO);
        }
        if (dbFlowChart.getDescription() == null) {
            dbFlowChart.setDescription("");
        }

        dbFlowChart.setDescription(dbFlowChart.getDescription() + MessageFormat.format("执行单据id:{0}->执行数:{1}；", documentId, zxs));
        execute = new BigDecimal("234234234");
        log.info("流程唯一识别:{}，执行单据id:{}，原值:{}，应执行{}，执行数{}，未执行数:{}，", documentId, "11", yz, yzx, zxs, nonExecute);
        return nonExecute;
    }
}
