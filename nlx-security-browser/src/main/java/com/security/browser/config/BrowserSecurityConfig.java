/*
 * 文件名：BrowserSecurityConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.browser.config;

import com.security.browser.authentication.MyAuthenticationFailedHandler;
import com.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 15:16:16
 * @see BrowserSecurityConfig
 * @since JDK1.8
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailedHandler authenticationFailedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用表单登录来进行身份认证
        // http.httpBasic()
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailedHandler)
                .and()
                //对授权的配置
                .authorizeRequests()
                //当访问/nlx-login.html不需要认证，就可以访问
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                //其他任何请求都需要身份认证
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}

