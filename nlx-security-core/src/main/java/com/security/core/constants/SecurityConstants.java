/*
 * 文件名：SecurityConstants.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.constants;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-13 10:54:54
 * @see SecurityConstants
 * @since JDK1.8
 */
public interface SecurityConstants {
    /**
     * 默认的用户名密码登录请求处理url(浏览器表单登录访问地址)
     * {@link com.security.browser.config.BrowserSecurityConfig#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
     * 配置的loginProcessingUrl
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 当请求需要身份认证时，默认跳转的url
     * {@link com.security.browser.config.BrowserSecurityConfig#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
     * 配置的loginPage
     * <p>
     * 及使用地方在 {@link com.security.browser.controller.BrowserSecurityController#requireAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 验证码session key
     */
    String DEFAULT_SESSION_IMAGE_CODE_KEY = "SESSION_KEY_IMAGE_CODE";
}
