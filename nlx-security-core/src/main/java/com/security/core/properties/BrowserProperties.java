/*
 * 文件名：BrowserProperties.java
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

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-12 0:16:16
 * @see BrowserProperties
 * @since JDK1.8
 */
@Data
public class BrowserProperties {
    //如果用户没有配值，就使用demo项目中的标准登录页面
    private String loginPage ="/nlx-login.html";

    /**
     * 根据配置实现要支持跳转或者返回json
     */
    private LoginType loginType = LoginType.JSON;

}