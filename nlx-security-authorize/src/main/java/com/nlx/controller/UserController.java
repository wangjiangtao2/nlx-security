/*
 * 文件名：UserController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-21 20:14:14
 * @see UserController
 * @since JDK1.8
 */
// 开启方法级别的权限控制
@EnableGlobalMethodSecurity(prePostEnabled =true)
@RestController
@RequestMapping("/users")
public class UserController {

    //只允许user角色访问
    @PreAuthorize("hasRole('user')")
    @RequestMapping("/{id}")
    public String index(@PathVariable("id") String id) {
        return "success " + id;
    }
}
