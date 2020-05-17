/*
 * 文件名：DefaultSmsValidateCodeSender.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认短信验证码发送接口
 */
@Slf4j
public class DefaultSmsValidateCodeSender implements SmsValidateCodeSender {
    /**
     * 实际生产环境中,调用渠道供应商发送短信
     */
    @Override
    public void send(String mobile, String code) {
        log.info("向手机[{}]发送验证码[{}]", mobile, code);
        log.info("短信渠道发送中...发送成功");
    }
}
