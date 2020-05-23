/*
 * 文件名：ResultBody.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一交互数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBody {
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 业务返回值
     */
    private Object data;

    public static ResultBody success(int code, String message) {
        return new ResultBody(code, message, null);
    }

    public static ResultBody success(int code, String message, Object data) {
        return new ResultBody(code, message, data);
    }

    public static ResultBody failed(int code) {
        return new ResultBody(code, null, null);
    }

    public static ResultBody failed(int code, String message) {
        return new ResultBody(code, message, null);
    }

    public static ResultBody failed(int code, String message, Object data) {
        return new ResultBody(code, message, data);
    }
}
