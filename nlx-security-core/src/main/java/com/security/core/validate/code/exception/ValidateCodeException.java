/*
 * 文件名：ValidateCodeException.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
