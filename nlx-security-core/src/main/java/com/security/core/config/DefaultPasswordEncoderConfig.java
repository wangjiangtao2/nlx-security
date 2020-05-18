/*
 * 文件名：DefaultPasswordConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;

/**
 * 默认密码配置类
 */
@Configuration
public class DefaultPasswordEncoderConfig {
    /**
     * 密码加密解密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
