/*
 * 文件名：SmsCodeSender.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.sms;

/**
 * 短信发送接口
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
