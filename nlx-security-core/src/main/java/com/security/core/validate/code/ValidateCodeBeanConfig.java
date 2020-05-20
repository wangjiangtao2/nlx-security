/*
 * 文件名：ValidateCodeBeanConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.security.core.properties.SecurityPropertie;
import com.security.core.validate.code.image.ImageValidateCodeGenerator;
import com.security.core.validate.code.sms.DefaultSmsValidateCodeSender;
import com.security.core.validate.code.sms.SmsValidateCodeGenerator;
import com.security.core.validate.code.sms.SmsValidateCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 验证码实例配置类
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * redis 操作工具类
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 验证码保存
     * 如果系统中有验证码保存类，就不会再次生成
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeStore.class)
    public ValidateCodeStore validateCodeStore() {
        DefaultValidateCodeStore validateCodeStore = new DefaultValidateCodeStore(redisTemplate);
        return validateCodeStore;
    }

    @Autowired
    private SecurityPropertie securityPropertie;

    /**
     * 图形验证码生成器
     * 如果系统中有图片验证码生成器，就不会再次生成
     */
    @Bean
    @ConditionalOnMissingBean(ImageValidateCodeGenerator.class)
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageCodeGenerator = new ImageValidateCodeGenerator(securityPropertie);
        return imageCodeGenerator;
    }


    /**
     * 短信验证码生成器
     * 如果系统中有短信验证码生成器，就不会再次生成
     */
    @Bean
    @ConditionalOnMissingBean(SmsValidateCodeGenerator.class)
    public ValidateCodeGenerator smsValidateCodeGenerator() {
        ValidateCodeGenerator smsCodeGenerator = new SmsValidateCodeGenerator();
        return smsCodeGenerator;
    }

    /**
     * 短信发送实例
     * 如果系统中有短信发送实例，就不会再次生成
     */
    @Bean
    @ConditionalOnMissingBean(SmsValidateCodeSender.class)
    public SmsValidateCodeSender smsValidateCodeSender() {
        DefaultSmsValidateCodeSender smsValidateCodeSender = new DefaultSmsValidateCodeSender();
        return smsValidateCodeSender;
    }


}
