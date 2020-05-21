/*
 * 文件名：WebSecurityConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SpringSecurity 安全配置类实现
 */
@Configuration
@EnableWebSecurity //1
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic();
        http.formLogin()  //2
                .and()
                //对授权的配置
                .authorizeRequests() //3
                //放行的路径集合
                .antMatchers("/index", "/").permitAll() //4
                //其他任何请求都需要身份认证
                .anyRequest().authenticated(); //6
    }



}

