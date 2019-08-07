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
    //订单池无订单
    NO_ORDER(-3, "订单池为空"),
    //订单池已满
    FULL_ORDER_POOL(-4, "订单池已满"),
    //无此订单
    NO_SUCH_ORDER(-5, "订单池中无此订单"),
    //生成订单号失败
    GENERATE_ORDER_NO_FAILED(-6, "生成订单号失败");

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
