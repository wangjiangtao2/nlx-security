/*
 * 文件名：SecurityProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;

import com.security.core.validate.code.properties.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 权限配置文件父类
 * 这里会有很多权限配置子模块
 */
@Data
@ConfigurationProperties(prefix = "nlx.security")
public class SecurityProperties {
    /**
     * 浏览器配置类
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码相关配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();
}
