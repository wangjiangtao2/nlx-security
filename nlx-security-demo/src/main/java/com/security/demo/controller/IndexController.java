/*
 * 文件名：IndexController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-17 9:37:37
 * @see IndexController
 * @since JDK1.8
 */
@RestController
public class IndexController {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 权限工具类 返回字符串权限集合
     * AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_ADMIN")
     * List<GrantedAuthority> createAuthorityList(String... authorities)
     */
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @RequestMapping("/index")
    public String  index() {
        redisTemplate.opsForValue().set("user", "fdskfjksdf");
        String user = (String) redisTemplate.opsForValue().get("user");
        System.out.println(user);
        return user;
    }
}
