/*
 * 文件名：SmsValidateCodeSender.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.sms;

/**
 * 短信验证码发送接口
 */
public interface SmsValidateCodeSender {
    void send(String mobile, String code);
}
