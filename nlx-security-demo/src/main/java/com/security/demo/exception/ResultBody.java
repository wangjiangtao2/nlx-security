/*
 * 文件名：ResultBody.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 10:30:30
 * @see ResultBody
 * @since JDK1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBody {
    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 业务返回值
     */
    private Object result;

    public static ResultBody success(String code, String message) {
        return new ResultBody(code, message, null);
    }

    public static ResultBody success(String code, String message, Object result) {
        return new ResultBody(code, message, result);
    }

    public static ResultBody failed(String code) {
        return new ResultBody(code, null, null);
    }

    public static ResultBody failed(String code, String message) {
        return new ResultBody(code, message, null);
    }
}
