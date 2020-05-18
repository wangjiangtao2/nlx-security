/*
 * 文件名：BrowserProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;

import com.security.core.enums.LoginTypeEnum;
import lombok.Data;

/**
 * 浏览器相关配置
 */
@Data
public class BrowserProperties {
    /**
     * 登录页面 不配置就使用core项目中的标准登录页面
     */
    private String loginPage = "/nlx-login.html";

    /**
     * 跳转类型 默认返回json数据
     */
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;

    /**
     * 记住我时间, 默认1小时
     */
    private int rememberMeSeconds = 3600;

}
