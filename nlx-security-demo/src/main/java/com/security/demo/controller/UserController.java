/*
 * 文件名：UserController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.controller;

import com.security.core.support.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-19 20:45:45
 * @see UserController
 * @since JDK1.8
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public ResultBody index() {
        redisTemplate.opsForValue().set("1","2");
        Object o = redisTemplate.opsForValue().get("1");
        return ResultBody.success(200, "查询成功",o);
    }
}
