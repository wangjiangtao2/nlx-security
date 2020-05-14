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
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";


    /**
     * 当请求需要身份认证时，默认跳转的url
     * {@link com.security.browser.config.BrowserSecurityConfig#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
     * 配置的loginPage
     * <p>
     * 及使用地方在 {@link com.security.browser.controller.BrowserSecurityController#requireAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";


    /******************************验证码相关 start*************************/
    /**
     * 发送短信验证码 或 验证短信验证码时 ，前端传递手机号的参数的名称
     */
     String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证码session key 前缀
     */
    String DEFAULT_SESSION_KEY_CODE_PREFIX = "SESSION_KEY_CODE_PREFIX_";

    /**
     * 图片验证码session key
     */
    String DEFAULT_SESSION_IMAGE_CODE_KEY = "SESSION_KEY_IMAGE_CODE";

    /**
     * 短信验证码session key
     */
    String DEFAULT_SESSION_SMS_CODE_KEY = "SESSION_KEY_SMS_CODE";

    /******************************验证码相关 end*************************/
}
