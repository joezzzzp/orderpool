package com.zzz.orderpool.advisor;

import com.zzz.orderpool.exception.BusinessException;
import com.zzz.orderpool.params.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzz
 * @date 2019/8/6 17:46
 **/

@RestControllerAdvice
public class BusinessExceptionAdvisor {

    @ExceptionHandler(BusinessException.class)
    Response handleBusinessException(HttpServletRequest request, BusinessException businessException) {
        return Response.failed(businessException.getErrorCode(), businessException.getErrorMessage(), null);
    }
}
