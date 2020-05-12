/*
 * 文件名：SecurityProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-12 0:15:15
 * @see SecurityProperties
 * @since JDK1.8
 */
@Data
@ConfigurationProperties(prefix = "nlx.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
