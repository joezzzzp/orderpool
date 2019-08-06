package com.zzz.orderpool.enumerate;

/**
 * @author zzz
 * @date 2019/8/6 17:59
 **/

public enum BusinessErrorEnum {
    //默认异常
    NONE(-1, "未知异常"),
    //超时异常
    TIME_OUT(-2, "连接超时"),
    NO_ORDER(-3, "订单池中没有订单");

    private int errorCode;

    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    BusinessErrorEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
