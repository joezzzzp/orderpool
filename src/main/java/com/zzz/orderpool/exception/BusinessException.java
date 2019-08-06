package com.zzz.orderpool.exception;

import com.zzz.orderpool.enumerate.BusinessErrorEnum;

/**
 * @author zzz
 * @date 2019/8/6 17:29
 **/

public class BusinessException extends RuntimeException{

    private int errorCode = -1;

    private String errorMessage = "业务异常";

    public BusinessException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static BusinessException build(BusinessErrorEnum businessErrorEnum) {
        return new BusinessException(businessErrorEnum.getErrorCode(), businessErrorEnum.getErrorMessage());
    }
}
