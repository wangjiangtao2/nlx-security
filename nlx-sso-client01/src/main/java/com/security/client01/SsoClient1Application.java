/*
 * 文件名：SsoClient1Application.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.client01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-16 15:53:53
 * @see SsoClient1Application
 * @since JDK1.8
 */
@EnableOAuth2Sso
@RestController
@SpringBootApplication
public class SsoClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(SsoClient1Application.class, args);
    }

    /**
     * 编写一个获取当前服务器的用户信息控制器
     */
    @GetMapping("/user")
    public Authentication user(Authentication user) {
        return user;
    }
}
