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

import com.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.security.core.constants.SecurityConstants;
import com.security.core.handler.DefaultAccessDeniedHandler;
import com.security.core.handler.DefaultAuthenticationEntryPoint;
import com.security.core.handler.DefaultAuthenticationFailedHandler;
import com.security.core.handler.DefaultAuthenticationSuccessHandler;
import com.security.core.handler.DefaultLogoutSuccessHandler;
import com.security.core.properties.SecurityPropertie;
import com.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    /**
     * 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
     */
    @Autowired
    private DefaultAccessDeniedHandler accessDeniedHandler;

    /**
     * 未登陆时返回 JSON 格式的数据给前端（否则为 html或者重定向）
     */
    @Autowired
    private DefaultAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
     */
    @Autowired
    private DefaultLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //将验证码过滤加在UsernamePasswordAuthenticationFilter过滤器之前
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);

        //对授权的配置
        http.authorizeRequests()
                //配置放行路径
                .antMatchers(
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.LOGIN_PROCESSING_URL_MOBILE,
                        "/demo-login.html",
                        "/code/*",
                        securityPropertie.getApp().getLoginPage()).permitAll()
                //其他任何请求都需要身份认证
                .anyRequest().authenticated();
        //关闭Session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.httpBasic()
                //未登录,返回JSON
                .authenticationEntryPoint(authenticationEntryPoint);

        //开启自动配置的登陆功能
        http.formLogin()
                //自定义标准登录界面
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                //自定义表单登录请求路径
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                //登录成功处理器
                .successHandler(successHandler)
                //登录失败处理器
                .failureHandler(failedHandler)

                .and()
                .exceptionHandling()
                //没有权限，返回json
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout()
                //退出成功，返回json
                .logoutSuccessHandler(logoutSuccessHandler);

        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();

        http.apply(smsCodeAuthenticationSecurityConfig);
    }

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
}














