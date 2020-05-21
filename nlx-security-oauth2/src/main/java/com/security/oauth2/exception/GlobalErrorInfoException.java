/*
 * 文件名：GlobalErrorInfoException.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.oauth2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一错误码异常
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GlobalErrorInfoException extends RuntimeException {
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;
}
