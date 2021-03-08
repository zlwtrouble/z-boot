package com.zhaolw.zoo.boot.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranj on 2017/7/18 0018.
 */
public enum ResultEnum {

    SUCCESS("success", "执行成功"),

    FAIL("fail", "执行失败"),

    PROCESSING("processing", "处理中"),

    SYSTEM_EXCEPTION("system_exception", "系统异常");

    private final String code;

    private final String message;

    /**
     *
     */
    private ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * @param code
     * @return ResultEnum
     */
    public static ResultEnum getByCode(String code) {
        for (ResultEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * @return List<ResultEnum>
     */
    public static java.util.List<ResultEnum> getAllEnum() {
        List<ResultEnum> list = new ArrayList<ResultEnum>(values().length);
        for (ResultEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * @return List<String>
     */
    public static java.util.List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>(values().length);
        for (ResultEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * @return
     */
    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        }
        ResultEnum _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    /**
     * @return
     */
    public static String getCode(ResultEnum resultEnum) {
        if (resultEnum == null) {
            return null;
        }
        return resultEnum.getCode();
    }
}
