package com.spring.boot.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranj on 2017/7/19 0019.
 *
 * 返回结果集
 */
public enum Status {

    SUCCESS("success", "执行成功"),

    FAIL("fail", "执行失败"),

    PROCESSING("processing", "处理中"),

    PARAMETER_NOT_EXIST("parameter_not_exist", "参数不存在"),

    /** 通用错误码 ******/
    /** 数据库异常 */
    DATABASE_EXCEPTION("database_exception", "数据库异常"),
    /** IO异常 */
    IO_EXCEPTION("io_exception", "IO异常"),
    /** 网络异常 */
    CONNECT_EXCEPTION("connect_exception", "网络异常"),
    /** 程序错误 */
    APP_ERROR("app_error", "程序错误"),
    /** 未知异常 */
    UNKNOWN_EXCEPTION("unknown_exception", "未知异常"),

    /** 系统内部错误码 ******/
    /** 系统内部错误 */
    SYSTEM_NEST_ERROR("A0B0_01_0001", "系统内部错误"),

    /** 非法的请求参数 */
    ILLEGAL_REQUEST_PARAMETER("illegal_request_parameter", "非法的请求参数"),

    /** 记录不存在 */
    RECORD_NOT_EXIST("record_not_exist", "记录不存在"),

    /** 操作不允许 */
    OPERATION_NOT_PERMIT("operation_not_permit", "操作不允许"),

    /** 查询记录失败 */
    QUERY_RECORD_FAIL("query_record_fail", "查询记录失败"),

    /** 用户真实姓名不合法！ */
    REALNAME_ERROR("realName_error", "用户真实姓名不合法！"),

    /** 用户手机号不合法！ */
    MOBLIE_ERROR("mobile_error", "用户手机号不合法！"),

    /** 用户邮箱不合法！ */
    EMAIL_ERROR("email_error", "用户邮箱不合法"),

    /** 重复推送 */
    REPEAT_PUSH("repeat_push", "重复推送"),

    SYSTEM_EXCEPTION("system_exception", "系统异常");

    private final String code;

    private final String message;

    /**
     *
     */
    private Status(String code, String message) {
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
     * @return Status
     */
    public static Status getByCode(String code) {
        for (Status _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public static String getCode(Status resultEnum) {
        if (resultEnum == null) {
            return null;
        }
        return resultEnum.getCode();
    }

    /**
     * @return
     */
    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        }
        Status _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    /**
     * @return List<Status>
     */
    public static List<Status> getAllEnum() {
        List<Status> list = new ArrayList<Status>(values().length);
        for (Status _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * @return List<String>
     */
    public static List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>(values().length);
        for (Status _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

}
