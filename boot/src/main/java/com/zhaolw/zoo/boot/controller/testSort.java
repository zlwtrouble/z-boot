package com.zhaolw.zoo.boot.controller;

import com.alibaba.fastjson.JSON;
import com.zhaolw.zoo.boot.common.utils.DateUtil;
import com.zhaolw.zoo.boot.vo.AgentInfoDetail;
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
//         testSort testSort = new testSort();
//         List<Integer> list=new ArrayList<>();
//         list.add(5);
//         list.add(5);
//         list.add(2);
//         list.add(3);
//         list.add(null);
//         list.add(0);
//         list.add(7);
//         list.add(null);
//         log.info(JSON.toJSONString(list));
//         testSort.sortOutboundDetailInfoListAsc(list);
//         log.info(JSON.toJSONString(list));

        List<AgentInfoDetail> agentInfoDetailList = new ArrayList<>();
        AgentInfoDetail agentInfoDetail1 = new AgentInfoDetail();
        agentInfoDetail1.setId(6L);
        agentInfoDetail1.setReferTime(DateUtil.parse("2019-12-16 12:55:65", DateUtil.YYMMDD_HHmmSS));
        agentInfoDetail1.setType(7);
        agentInfoDetailList.add(agentInfoDetail1);

        AgentInfoDetail agentInfoDetail2 = new AgentInfoDetail();
        agentInfoDetail2.setId(9L);
        agentInfoDetail2.setReferTime(DateUtil.parse("2019-12-16 12:55:65", DateUtil.YYMMDD_HHmmSS));
        agentInfoDetail2.setType(1);
        agentInfoDetailList.add(agentInfoDetail2);

        AgentInfoDetail agentInfoDetail3 = new AgentInfoDetail();
        agentInfoDetail3.setId(5L);
        agentInfoDetail3.setReferTime(DateUtil.parse("2019-12-16 12:55:60", DateUtil.YYMMDD_HHmmSS));
        agentInfoDetail3.setType(1);
        agentInfoDetailList.add(agentInfoDetail3);

        AgentInfoDetail agentInfoDetail4 = new AgentInfoDetail();
        agentInfoDetail4.setId(10L);
        agentInfoDetail4.setReferTime(DateUtil.parse("2019-12-16 12:55:62", DateUtil.YYMMDD_HHmmSS));
        agentInfoDetail4.setType(1);
        agentInfoDetailList.add(agentInfoDetail4);

        AgentInfoDetail agentInfoDetail5 = new AgentInfoDetail();
        agentInfoDetail5.setId(1L);
        agentInfoDetail5.setReferTime(DateUtil.parse("2019-12-16 12:55:67", DateUtil.YYMMDD_HHmmSS));
        agentInfoDetail5.setType(1);
        agentInfoDetailList.add(agentInfoDetail5);

        AgentInfoDetail agentInfoDetail6 = new AgentInfoDetail();
        agentInfoDetail6.setId(3L);
        agentInfoDetail6.setReferTime(DateUtil.parse("2019-12-16 12:55:67", DateUtil.YYMMDD_HHmmSS));
        agentInfoDetail6.setType(6);
        agentInfoDetailList.add(agentInfoDetail6);
        testSort testSort = new testSort();
        testSort.sortTest2(agentInfoDetailList);
        for (AgentInfoDetail agentInfoDetail : agentInfoDetailList) {
            System.out.println("" + JSON.toJSONString(agentInfoDetail));
        }
    }

    private void sortOutboundDetailInfoListAsc(List<Integer> outboundDetailInfoList) {
        outboundDetailInfoList.sort((o1, o2) -> {
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            //升序 null放最后  降序加负号
            return Integer.compare(o1.compareTo(o2), 0);
        });
    }


    private void sortTest2(List<AgentInfoDetail> agentInfoDetailList) {
        agentInfoDetailList.sort((o1, o2) -> {
            if (o1 == null || o2 == null || o1.getReferTime() == null || o2.getReferTime() == null) {
                return -1;
            }
            if (o1.getReferTime().compareTo(o2.getReferTime()) == 0) {
                if (o1.getType() == 7) {
                    return -1;
                } else {
                    return Integer.compare(o1.getId().compareTo(o2.getId()), 0);
                }
            }
            return Integer.compare(o1.getReferTime().compareTo(o2.getReferTime()), 0);
        });
    }
}
