package com.zzz.orderpool.advisor;

import com.zzz.orderpool.exception.BusinessException;
import com.zzz.orderpool.params.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zzz
 * @date 2019/8/6 17:46
 **/

@RestControllerAdvice
public class BusinessExceptionAdvisor {

    private static final Logger logger = LoggerFactory.getLogger(BusinessExceptionAdvisor.class);

    @ExceptionHandler(BusinessException.class)
    Response handleBusinessException(BusinessException businessException) {
        logger.error("Business error", businessException);
        return Response.failed(businessException.getErrorCode(), businessException.getMessage(), null);
    }
}
