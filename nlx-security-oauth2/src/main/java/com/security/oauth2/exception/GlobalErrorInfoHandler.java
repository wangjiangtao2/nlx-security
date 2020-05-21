/*
 * 文件名：GlobalErrorInfoHandler.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.oauth2.exception;

import com.security.oauth2.support.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * 统一错误码异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalErrorInfoHandler {

    /**
     * Description
     * 处理业务自定义异常
     **/
    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody serviceException(
            GlobalErrorInfoException ex, HandlerMethod handlerMethod) {
        log.info("class [{}], methodName [{}], code[{}] message[{}]", handlerMethod.getBean().getClass(),
                handlerMethod.getMethod().getName(), ex.getCode(), ex.getMessage());
        return ResultBody.failed(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理其他异常
     **/
    @ExceptionHandler(Exception.class)
    public ResultBody handlerException(Exception ex, HandlerMethod handlerMethod) {
        log.info("class [{}], methodName [{}], code[{}] message[{}]", handlerMethod.getBean().getClass(),
                handlerMethod.getMethod().getName(), ex.getMessage());
        return ResultBody.failed(500, "处理其他异常");
    }
}
