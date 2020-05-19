/*
 * 文件名：SecurityPropertiesCoreConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 让 SecurityProperties配置文件生效
 */
@Configuration
@EnableConfigurationProperties(SecurityPropertie.class)
public class SecurityPropertiesCoreConfig {
}
