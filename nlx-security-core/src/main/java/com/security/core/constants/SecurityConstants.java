/*
 * 文件名：SecurityConstants.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.constants;

public interface SecurityConstants {
    /**
     * 默认的用户名密码登录请求处理url(浏览器表单登录访问地址)
     * {@link }
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 当请求需要身份认证时，默认处理的url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
}
