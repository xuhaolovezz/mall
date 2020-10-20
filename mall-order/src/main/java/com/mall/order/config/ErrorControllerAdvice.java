package com.mall.order.config;

import com.mall.common.exception.BusinessException;
import com.mall.common.vo.MallResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object errorHandle(Exception e){
        String errMsg = e.getMessage();
        if(e instanceof BusinessException || (e instanceof RuntimeException && errMsg.contains("BusinessException"))){
            log.error(errMsg);
        }else {
            log.error("", e);
        }
        return MallResult.fail("500",errMsg);
    }

}
