/*
 * 文件名：ValidateCodeProcessor.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器 功能定义
 */
public interface ValidateCodeProcessor {
    /**
     * 创建验证码(创建、保存、发送)
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     */
    void validate(ServletWebRequest request);

    /**
     * 是否支持
     *
     * @param type
     */
    boolean supports(String type);

}
