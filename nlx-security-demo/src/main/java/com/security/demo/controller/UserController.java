/*
 * 文件名：UserController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 认证主体信息
     */
    @GetMapping("/me")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails user){
        return user;
    }

    /**
     *获取用户认证信息
     */
    @GetMapping("/authentication")
    public Authentication getCurrentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户认证信息
     * 同getCurrentAuthentication spring 会帮我们注入
     */
    @GetMapping("/authentication/auto")
    public Authentication getCurrentAuthentication2(Authentication authentication){
        return authentication;
    }
}
