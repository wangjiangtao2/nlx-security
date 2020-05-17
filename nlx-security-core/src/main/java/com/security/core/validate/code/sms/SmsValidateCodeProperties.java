/*
 * 文件名：SmsCodeProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.sms;

import lombok.Data;

import java.util.Set;

/**
 * 短信验证码配置类
 */
@Data
public class SmsValidateCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 验证码有效时间 60s
     */
    private int expireIn = 60;
    /**
     * 需要拦截的url
     */
    private Set<String> urls;
}
