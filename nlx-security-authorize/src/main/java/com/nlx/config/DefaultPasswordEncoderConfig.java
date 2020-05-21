/*
 * 文件名：DefaultPasswordConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *默认密码配置类
 */
@Configuration
public class DefaultPasswordEncoderConfig {

    /**
     * 密码加密及验证
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * 采用 SHA-256 +随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法
         */
        return new BCryptPasswordEncoder();
    }
}
