package com.zhaolw.zoo.boot.testapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/5/24 09:29
 **/
@Slf4j
public class OnsitApi {

    public static void main(String[] args) {

        OnsitApi onsitApi = new OnsitApi();

        //合同名改下,执行2次
        onsitApi.addSalesContract(UUID.randomUUID().toString().substring(0, 5));
        onsitApi.addSalesContract2(UUID.randomUUID().toString().substring(0, 5));
        onsitApi.addSalesContract3(UUID.randomUUID().toString().substring(0, 5));
        //加库存执行2次
        //onsitApi.addInventory();
        // onsitApi.addInventory();

        // onsitApi.addOrder();
    }

    /**
     * 现场领料
     *
     * @return void
     */
    private void addOrder() {


        String url = ApiParam.urlPrefix + "/wms/onSite/picking";

        String jsonStr = "{\"pickingUse\":null,\"pickerRemark\":\"主表备注\",\"customerId\":\"284391622353883136\",\"departmentId\":\"284398467214741504\",\"pickerName\":\"\",\"pickerPhone\":\"\",\"requiredArrivalTime\":\"\",\"items\":[{\"skuId\":\"276288283036692480\",\"goodsOwnerId\":\"275629552523284480\",\"amount\":6,\"remark\":\"011\"},{\"skuId\":\"276712584734912512\",\"goodsOwnerId\":\"275629552523284480\",\"amount\":6,\"remark\":\"012\"},{\"skuId\":\"276713479715500032\",\"goodsOwnerId\":\"275629552523284480\",\"amount\":6,\"remark\":\"013\"},{\"skuId\":\"276713939218280448\",\"goodsOwnerId\":\"275629552523284480\",\"amount\":6,\"remark\":\"014\"},{\"skuId\":\"276714012278861824\",\"goodsOwnerId\":\"275629552523284480\",\"amount\":6,\"remark\":\"014\"},{\"skuId\":\"276715544827211776\",\"goodsOwnerId\":\"275629552523284480\",\"amount\":6,\"remark\":\"016\"}]}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", ApiParam.token);
        requestHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        String s = jsonObject.toJSONString();
        log.info("JSON串" + s);
        HttpEntity<String> requestEntity = new HttpEntity<String>(s, requestHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        log.info("请求结果：" + exchange);
    }

    /**
     * 库存
     *
     * @return void
     */
    private void addInventory() {

        String url = ApiParam.urlPrefix + "wms/inbound/saveInbound";

        String jsonStr = "{\"inboundNo\":\"WV0014201905240001\",\"relationNo\":\"\",\"contactName\":\"李雪峰\",\"remark\":\"\",\"shipperType\":\"CLIENT\",\"contactNumber\":\"13305799639\",\"shipperUkid\":\"275629552523284480\",\"whInboundDetailVos\":[{\"unitPrice\":11.111111,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"284391622353883136\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276288283036692480\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"施耐德 小型断路器 A9N19822 C120 100A AC400 10KA 3P D型\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"273729204603129856\",\"unit\":\"只\"},{\"unitPrice\":12.222222,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"284391622353883136\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276712584734912512\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"容鑫/RXiN 电容器 CBB61 450V AC 2.2μF±5% 50/60Hz 电机、泵、洗衣机、节电器\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"273729204603129856\",\"unit\":\"只\"},{\"unitPrice\":13.333333,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"284391622353883136\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276713479715500032\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"湘联 电线 60227IEC01(BV) 100m*1mm² 450/750V 蓝色\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"276712970929647616\",\"unit\":\"米\"},{\"unitPrice\":14.444444,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"275629552523284480\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276713939218280448\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"湘联 电线 60227IEC01(BV) 100m*1mm² 450/750V 红色\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"276712970929647616\",\"unit\":\"米\"},{\"unitPrice\":15.555555,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"275629552523284480\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276714012278861824\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"永乐/Yolo 其它配件 4A 250V 螺口灯座\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"273729204603129856\",\"unit\":\"只\"},{\"unitPrice\":16.666666,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"275629552523284480\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276715544827211776\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"元通 电线 ZR-BV 100m*2.5mm² 450/750V 红色\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"276712970929647616\",\"unit\":\"米\"},{\"unitPrice\":11,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"275629552523284480\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276288283036692480\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"施耐德 小型断路器 A9N19822 C120 100A AC400 10KA 3P D型\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"273729204603129856\",\"unit\":\"只\"},{\"unitPrice\":12,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"275629552523284480\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276712584734912512\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"容鑫/RXiN 电容器 CBB61 450V AC 2.2μF±5% 50/60Hz 电机、泵、洗衣机、节电器\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"273729204603129856\",\"unit\":\"只\"},{\"unitPrice\":13,\"num\":3,\"supplierUkid\":\"\",\"ownerUkid\":\"275629552523284480\",\"remark\":\"\",\"taxRate\":0.11,\"skuUkid\":\"276713479715500032\",\"skuCode\":\"\",\"actualLocationUkid\":\"284399928007012352\",\"actualLocation\":\"\",\"productionDate\":\"\",\"name\":\"湘联 电线 60227IEC01(BV) 100m*1mm² 450/750V 蓝色\",\"quantityScale\":0,\"visible\":false,\"unitUkid\":\"276712970929647616\",\"unit\":\"米\"}]}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", ApiParam.token);
        requestHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        String s = jsonObject.toJSONString();
        log.info("JSON串" + s);
        HttpEntity<String> requestEntity = new HttpEntity<String>(s, requestHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        log.info("请求结果：" + exchange);
    }

    /**
     * 新增销售合同
     *
     * @return void
     */
    private void addSalesContract(String name) {

        String url = ApiParam.urlPrefix + "contract/salesContract/addSalesContract";

        String jsonStr = "{\n" +
                "    \"contractName\": \"现场领料绑定本地共享仓客户2019-0524-03\",\n" +
                "    \"contractNo\": \"XSHT1905240005\",\n" +
                "    \"customerId\": \"284391622353883136\",\n" +
                "    \"goodsList\": [\n" +
                "        {\n" +
                "            \"contractPrice\": 111,\n" +
                "            \"goodsId\": \"276288283040886784\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 112,\n" +
                "            \"goodsId\": \"276712584739106816\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 113,\n" +
                "            \"goodsId\": \"276713479719694336\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 114,\n" +
                "            \"goodsId\": \"276713939222474752\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 115,\n" +
                "            \"goodsId\": \"276714012283056128\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 116,\n" +
                "            \"goodsId\": \"276715544831406080\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        }\n" +
                "    ],\n" +
                "    \"priceStrategy\": \"1\",\n" +
                "    \"signAddress\": \"浙江省-金华-兰溪市-上华街道 迎宾大道588号\",\n" +
                "    \"validDate\": \"2019-05-23T16:00:00.000Z\"\n" +
                "}";

        jsonStr = jsonStr.replace("2019-0524-03", name);
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", ApiParam.token);
        requestHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        String s = jsonObject.toJSONString();
        log.info("JSON串" + s);
        HttpEntity<String> requestEntity = new HttpEntity<String>(s, requestHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        log.info("请求结果：" + exchange);
    }


    /**
     * 新增销售合同
     *
     * @return void
     */
    private void addSalesContract2(String name) {

        String url = ApiParam.urlPrefix + "contract/salesContract/addSalesContract";

        String jsonStr = "{\n" +
                "    \"contractName\": \"现场领料绑定本地共享仓客户2019-0524-03\",\n" +
                "    \"contractNo\": \"XSHT1905240005\",\n" +
                "    \"customerId\": \"284391622353883136\",\n" +
                "    \"goodsList\": [\n" +
                "        {\n" +
                "            \"contractPrice\": 121,\n" +
                "            \"goodsId\": \"276288283040886784\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 122,\n" +
                "            \"goodsId\": \"276712584739106816\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 123,\n" +
                "            \"goodsId\": \"276713479719694336\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 124,\n" +
                "            \"goodsId\": \"276713939222474752\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 125,\n" +
                "            \"goodsId\": \"276714012283056128\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 126,\n" +
                "            \"goodsId\": \"276715544831406080\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        }\n" +
                "    ],\n" +
                "    \"priceStrategy\": \"1\",\n" +
                "    \"signAddress\": \"浙江省-金华-兰溪市-上华街道 迎宾大道588号\",\n" +
                "    \"validDate\": \"2019-05-23T16:00:00.000Z\"\n" +
                "}";

        jsonStr = jsonStr.replace("2019-0524-03", name);
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", ApiParam.token);
        requestHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        String s = jsonObject.toJSONString();
        log.info("JSON串" + s);
        HttpEntity<String> requestEntity = new HttpEntity<String>(s, requestHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        log.info("请求结果：" + exchange.getBody());
    }


    /**
     * 新增销售合同
     *
     * @return void
     */
    private void addSalesContract3(String name) {

        String url = ApiParam.urlPrefix + "contract/salesContract/addSalesContract";

        String jsonStr = "{\n" +
                "    \"contractName\": \"现场领料绑定本地共享仓客户2019-0524-03\",\n" +
                "    \"contractNo\": \"XSHT1905240005\",\n" +
                "    \"customerId\": \"284391622353883136\",\n" +
                "    \"goodsList\": [\n" +
                "        {\n" +
                "            \"contractPrice\": 131,\n" +
                "            \"goodsId\": \"276288283040886784\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 132,\n" +
                "            \"goodsId\": \"276712584739106816\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 133,\n" +
                "            \"goodsId\": \"276713479719694336\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 134,\n" +
                "            \"goodsId\": \"276713939222474752\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 135,\n" +
                "            \"goodsId\": \"276714012283056128\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"contractPrice\": 136,\n" +
                "            \"goodsId\": \"276715544831406080\",\n" +
                "            \"supplyAmount\": 2\n" +
                "        }\n" +
                "    ],\n" +
                "    \"priceStrategy\": \"1\",\n" +
                "    \"signAddress\": \"浙江省-金华-兰溪市-上华街道 迎宾大道588号\",\n" +
                "    \"validDate\": \"2019-05-23T16:00:00.000Z\"\n" +
                "}";

        jsonStr = jsonStr.replace("2019-0524-03", name);
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", ApiParam.token);
        requestHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        String s = jsonObject.toJSONString();
        log.info("JSON串" + s);
        HttpEntity<String> requestEntity = new HttpEntity<String>(s, requestHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        log.info("请求结果：" + exchange);
    }

}
