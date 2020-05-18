/*
 * 文件名：BrowserSecurityConfig.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.browser.config;

import com.security.core.constants.SecurityConstants;
import com.security.core.handler.DefaultAuthenticationFailedHandler;
import com.security.core.handler.DefaultAuthenticationSuccessHandler;
import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 浏览器security配置类
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

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
     * 权限配置文件父类
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 验证码过滤器
     */
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private DataSource dataSource;

    /**
     * 用户信息获取(比如从数据库获取用户信息)
     * {@link com.security.browser.service.UserDetailServiceImpl}
     */
    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * 记住我持久化数据源
     * JdbcTokenRepositoryImpl  CREATE_TABLE_SQL 建表语句可以先在数据库中执行
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        /**具体表在
         * {@link org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl.CREATE_TABLE_SQL}
         */
        /**
         * 在启动的时候去创建表,但是项目多次启动会报错，不能创建同名表
         * 所以还是手工创建表
         */
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //spring5后默认就是表单登录方式
                // httpBasic().
                .formLogin()
                //自定义登录请求
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                //自定义表单登录地址
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(successHandler)
                .failureHandler(failedHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)

                .and()
                //对授权的配置
                .authorizeRequests()
                //此路径放行 否则会陷入死循环
                .antMatchers(
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        "/code/*",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                //其他任何请求都需要身份认证
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
