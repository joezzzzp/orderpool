package com.zzz.orderpool.exception;

import com.zzz.orderpool.enumerate.BusinessErrorEnum;

/**
 * @author zzz
 * @date 2019/8/6 17:29
 **/

public class BusinessException extends RuntimeException{

    private int errorCode = -1;

    public BusinessException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static BusinessException build(BusinessErrorEnum businessErrorEnum) {
        return new BusinessException(businessErrorEnum.getErrorCode(), businessErrorEnum.getErrorMessage());
    }
}
