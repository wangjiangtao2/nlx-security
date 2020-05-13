/*
 * 文件名：ValidateCodeGenerator.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器接口
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
