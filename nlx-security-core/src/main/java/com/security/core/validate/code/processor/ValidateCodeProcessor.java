/*
 * 文件名：ValidateCodeProcessor.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器接口
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
}
