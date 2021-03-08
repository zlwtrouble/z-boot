package com.zhaolw.zoo.boot.testapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.zhaolw.zoo.boot.entity.InventoryTask;
import com.zhaolw.zoo.boot.entity.StoreHouse;
import com.zhaolw.zoo.boot.vo.BaseResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/5/24 09:29
 **/
@Slf4j
public class PdApi {

    public static void main(String[] args) {

        PdApi onsitApi = new PdApi();

        //合同名改下,执行2次
        onsitApi.addOrder2(null);

    }

    /**
     * 现场领料
     *
     * @return void
     */
    private void addOrder(Long warehouseId) {


        String url = ApiParam.urlPrefix + "wms/inventoryTask/getInventoryShelf?warehouseId={warehouseId}&token={token}";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        Map<String, Object> uriVariables = new HashMap<String, Object>(16);
        uriVariables.put("warehouseId", warehouseId);
        uriVariables.put("token", ApiParam.token);

        ResponseEntity<BaseResultVo> exchange = restTemplate.getForEntity(url, BaseResultVo.class, uriVariables);


        log.info("请求结果：" + exchange);
        int i = Integer.valueOf(exchange.getBody().getData().toString());
        this.addOrder3(i, warehouseId);
    }


    /**
     * 现场领料
     *
     * @return void
     */
    private void addOrder2(Long warehouseId) {


        String url = ApiParam.urlPrefix + "wms/inventoryTask/getStoreHouseByInventoryTask?token={token}";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("token", ApiParam.token);

        ResponseEntity<BaseResultVo> exchange = restTemplate.getForEntity(url, BaseResultVo.class, uriVariables);

        String s = JSON.toJSONString(exchange.getBody().getData());
        log.info("请求结果：" + s);
        PageInfo<StoreHouse> storeHousePageInfo = JSON.parseObject(s, new TypeReference<PageInfo<StoreHouse>>() {
        });
        for (StoreHouse storeHouse : storeHousePageInfo.getList()) {
            this.addOrder(storeHouse.getId());
        }
    }


    /**
     * 库存
     *
     * @return void
     */
    private void addOrder3(Integer shelf, Long warehouseId) {
        if (shelf == 0) {
            return;
        }
        String url = ApiParam.urlPrefix + "/wms/inventoryTask/addInventoryTask";

        InventoryTask inventoryTask = new InventoryTask();
        inventoryTask.setShelfCount(shelf);
        if (shelf < 4) {
            inventoryTask.setDayCount(1);
        } else {
            inventoryTask.setDayCount(4);
        }
        inventoryTask.setHasZero(1);
        inventoryTask.setIntegerType(1);
        inventoryTask.setWarehouseId(warehouseId);
        String s = JSONObject.toJSONString(inventoryTask);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", ApiParam.token);
        requestHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
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
        log.info("请求结果：" + exchange);
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
