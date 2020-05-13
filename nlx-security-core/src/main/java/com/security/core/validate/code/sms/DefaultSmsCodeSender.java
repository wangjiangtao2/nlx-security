/*
 * 文件名：DefaultSmsCodeSender.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认短信发送接口
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("向手机[{}]发送验证码[{}]", mobile, code);
    }
}
