/*
 * 文件名：ValidateCodeProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.properties;

import com.security.core.validate.code.sms.SmsValidateCodeProperties;
import lombok.Data;

/**
 * 验证码配置
 */
@Data
public class ValidateCodeProperties {
    /**
     * 图片验证码配置
     */
    private ImageValidateCodeProperties image = new ImageValidateCodeProperties();

    /**
     * 短信验证码配置
     */
    private SmsValidateCodeProperties sms = new SmsValidateCodeProperties();
}
