/*
 * 文件名：ValidateCodeStore.java
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
 * 验证码存储接口
 */
public interface ValidateCodeStore {

    /**
     * 保存验证码
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);
}
