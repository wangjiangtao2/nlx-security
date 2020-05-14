/*
 * 文件名：ValidateCodeBeanConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.image.ImageValidateCodeGenerator;
import com.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码实例配置类
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图形验证码生成器
     * 如果系统中有图片验证码生成器，就不会再次生成
     */
    @Bean
    @ConditionalOnMissingBean(ImageValidateCodeGenerator.class)
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageCodeGenerator = new ImageValidateCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    /**
     * 短信发送实例
     * 如果系统中有短信发送实例，就不会再次生成
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
