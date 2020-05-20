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
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 手机号登录请求处理url
     */
    String LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 当请求需要身份认证时，默认处理的url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";


/******************************验证码相关 start*************************/
    /**
     * 发送短信验证码 或 验证短信验证码时 ，前端传递手机号的参数的名称
     */
    String PARAMETER_NAME_MOBILE = "mobile";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证码 存储key 前缀
     */
    String VALIDATE_CODE_KEY_PREFIX = "VALIDATE_CODE_KEY_PREFIX_";

    /**
     * 设备ID 入参，http请求中默认的携带设备号信息的参数的名称
     */
    String HEAD_NAME_DEVICE_ID = "deviceId";
/******************************验证码相关 end*************************/


}
