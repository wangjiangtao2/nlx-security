/*
 * 文件名：AppSecurityConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.config;

import com.security.core.constants.SecurityConstants;
import com.security.core.handler.DefaultAuthenticationFailedHandler;
import com.security.core.handler.DefaultAuthenticationSuccessHandler;
import com.security.core.properties.SecurityPropertie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security配置类
 */
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 权限配置文件父类
     */
    @Autowired
    private SecurityPropertie securityPropertie;

    /**
     * 登录成功处理器
     */
    @Autowired
    private DefaultAuthenticationSuccessHandler successHandler;
    /**
     * 登录失败处理器
     */
    @Autowired
    private DefaultAuthenticationFailedHandler failedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //spring5后默认就是表单登录方式
                // httpBasic().
                .formLogin()
                //自定义登录请求
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                //自定义表单登录请求路径
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                //登录成功处理器
                .successHandler(successHandler)
                //登录失败处理器
                .failureHandler(failedHandler)
                .and()
                //对授权的配置
                .authorizeRequests()
                //此路径放行 否则会陷入死循环
                .antMatchers(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        "/demo-login.html",
                        securityPropertie.getApp().getLoginPage()).permitAll()
                //其他任何请求都需要身份认证
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
