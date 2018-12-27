package com.spring.boot.common.enums;

/**
 * @author zhaoliwei
 * @description:
 * @date 2018/12/26 11:28
 **/
public enum MessageType {
    CONNECT_REQ((byte) 1),
    CONNECT_SUCCESS((byte) 2),
    CONNECT_FAIL((byte) 3),
    HEARTBEAT_REQ((byte) 4),
    HEARTBEAT_RESP((byte) 5),
    MSG_PUSH((byte) 6);

    private byte value;

    private MessageType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }


}
