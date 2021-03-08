package com.zhaolw.zoo.boot.common.result;

import com.zhaolw.zoo.boot.common.enums.Status;
import org.springframework.beans.BeanUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Ranj on 2017/7/19 0019.
 * <p>
 * 返回基类，所有返回类（XxxResult）都继承该类，如有特殊需要可不是使用
 */

public class BaseResult {

    public static final String FAIL_CODE = "FAIL";


    /**
     * 结果状态
     */
    protected Status status;

    /**
     * 信息码
     */
    protected String code;

    /**
     * 描述
     */
    protected String description;

    /**
     * 参数map
     */
    protected Map<Object, Object> params = new LinkedHashMap<Object, Object>();

    /**
     * 判断是否为成功。
     *
     * @return 如果成功返回true。
     */
    public boolean isSuccess() {
        return this.status == Status.SUCCESS;
    }

    /**
     * 判断是否失败。失败成功返回true。
     */
    public boolean isFail() {
        return this.status == Status.FAIL;
    }

    /**
     * 判断是否处理中。
     *
     * @return 如果处理中返回true。
     */
    public boolean isProcessing() {
        return this.status == Status.PROCESSING;
    }


    /**
     * 执行成功
     *
     * @return BaseResult
     */
    public void SUCCESS() {
        this.setStatus(Status.SUCCESS);
        //BaseResult result = new BaseResult();
        //return result;
    }

    /**
     * 执行成功（带参）
     *
     * @param baseResult
     * @return
     */
    public BaseResult SUCCESS(BaseResult baseResult) {
        BaseResult result = new BaseResult();
        BeanUtils.copyProperties(baseResult, result);
        this.setStatus(Status.SUCCESS);
        return result;
    }


    /**
     * 通过Exception创建一个BaseResult
     *
     * @param ex    OrderCheckException
     * @param clazz 结果类型
     * @param <T>
     * @return
     */
    public static <T extends BaseResult> T from(Exception ex, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            t.setStatus(Status.FAIL);
            t.setCode(FAIL_CODE);
            t.setDescription(ex.getMessage());
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.code = status.getCode();
        this.description = status.getMessage();
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Object, Object> getParams() {
        return params;
    }

    public void setParams(Map<Object, Object> params) {
        this.params = params;
    }
}
