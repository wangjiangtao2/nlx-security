/*
 * 文件名：AppProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;

import lombok.Data;

/**
 * APP端相关配置
 */
@Data
public class AppProperties {
    /**
     * 登录页面 不配置就使用core项目中的标准登录页面
     */
    private String loginPage = "/nlx-login.html";
}
