package com.zhaolw.zoo.boot.controller;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SimpleClient {

    public static void main(String[] args) throws Exception {
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("192.168.173.67", 11111), "example", "", "");

        connector.connect();
        connector.subscribe();
        connector.rollback();

        while (true) {
            Message message = connector.getWithoutAck(100);
            long batchId = message.getId();
            if (batchId == -1 || message.getEntries().isEmpty()) {
                System.out.println("sleep");
                Thread.sleep(1000);
                continue;
            }
            printEntries(message.getEntries());
            //connector.ack(batchId);
        }
    }

    private static void printEntries(java.util.List<CanalEntry.Entry> entries) throws Exception {
        for (CanalEntry.Entry entry : entries) {
            long logfileOffset = entry.getHeader().getLogfileOffset();
            log.info("logfileOffset:" + logfileOffset);
            if (entry.getEntryType() != CanalEntry.EntryType.ROWDATA) {
                // continue;
            }


            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            if (rowChange.getEventType().equals(CanalEntry.EventType.ALTER)
                    || rowChange.getEventType().equals(CanalEntry.EventType.CINDEX)) {
                String sql = rowChange.getSql();

                System.out.print("ALTER " + sql);
            }

            if (rowChange.getEventType().equals(CanalEntry.EventType.DINDEX)
            ) {
                String sql = rowChange.getSql();

                System.out.print("ALTER " + sql);
            }


            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                switch (rowChange.getEventType()) {
                    case INSERT:
                    case UPDATE:
                        System.out.print("UPSERT ");
                        java.util.List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                        printColumns(afterColumnsList);

                        if ("retl_buffer".equals(entry.getHeader().getTableName())) {
                            String tableName = rowData.getAfterColumns(1).getValue();
                            String pkValue = rowData.getAfterColumns(2).getValue();
                            System.out.println("SELECT * FROM " + tableName + " WHERE id = " + pkValue);
                        }
                        break;

                    case DELETE:
                        System.out.print("DELETE ");
                        printColumns(rowData.getBeforeColumnsList());
                        break;


                    default:
                        break;
                }
            }
        }
    }

    private static void printColumns(java.util.List<CanalEntry.Column> columns) {
        String line = columns.stream()
                .map(column -> column.getName() + "=" + column.getValue())
                .collect(Collectors.joining(","));
        System.out.println(line);

        String sql = "INSERT INTO " + "table (";
        StringBuffer colums = new StringBuffer();
        StringBuffer position = new StringBuffer();
        java.util.List<String> valueList = new ArrayList<>();
        columns.forEach((c) -> {
            colums.append(c.getName() + ",");
            if (c.getIsNull()) {
                position.append("?,");
                valueList.add(null);

            } else {
                position.append("?,");
                valueList.add(c.getValue());
            }
        });


//        sql += "INSERT INTO " + "table" + "(" + colums.substring(0, colums.length() - 1) + ") VALUES(" + position.substring(0, position.length() - 1) + ");";
//        System.out.println("insert:"+sql);

    }


}
