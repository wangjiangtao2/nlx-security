/*
 * 文件名：GlobalErrorInfoException.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.exception;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 10:30:30
 * @see GlobalErrorInfoException
 * @since JDK1.8
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一错误码异常
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GlobalErrorInfoException extends Exception {
    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;
}
