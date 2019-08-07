package com.zzz.orderpool.enumerate;

/**
 * @author zzz
 * @date 2019/8/6 17:59
 **/

public enum BusinessErrorEnum {
    //Default error
    NONE(-1, "Unknown error"),
    //Time out
    TIME_OUT(-2, "Time out"),
    //Empty order pool
    NO_ORDER(-3, "Order pool is empty"),
    //Full order pool
    FULL_ORDER_POOL(-4, "Order pool is full"),
    //Have no such order
    NO_SUCH_ORDER(-5, "Have no such order in order pool"),
    //Can't generate order no
    GENERATE_ORDER_NO_FAILED(-6, "Generate order no error");

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
