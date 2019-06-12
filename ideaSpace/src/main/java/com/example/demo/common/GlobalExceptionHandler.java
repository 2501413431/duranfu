package com.example.demo.common;

import com.example.demo.common.Exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/4/26
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GlobalResult jsonErrorHandler(HttpServletRequest req, Exception e) {
        log.info("************GlobalExceptionHandler*************");
        try {
            String channel = req.getParameter("channel");
            String reqUri = req.getRequestURI();
            if (e instanceof BusinessException) {
                BusinessException businessException = (BusinessException)e;
                Throwable cause = e.getCause();
                if(null != cause){
                    log.error("{}: 接口{}异常结束，异常信息={}", channel, reqUri, businessException.getMessage(), cause);
                }else{
                    log.error("{}: 接口{}异常结束，异常信息={}", channel, reqUri, businessException.getMessage());
                }
                return new GlobalResult(businessException.getCode(), businessException.getMsg());
            } else {
                log.error("{}: 接口{}异常结束", channel, reqUri, e);
                return GlobalResult.failure();
            }
        } catch (Exception e2) {
            log.error("处理异常时出现异常", e2);
            return GlobalResult.failure();
        }
    }
}
