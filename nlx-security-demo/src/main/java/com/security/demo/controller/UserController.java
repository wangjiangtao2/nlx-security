/*
 * 文件名：UserController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.controller;

import com.security.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 9:45:45
 * @see UserController
 * @since JDK1.8
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User userInfo(@PathVariable String id) {
        log.info("id [{}]", id);
        return new User("tjs", "123");
    }
    /*@GetMapping("/user/{id}:\\d+")
    public User userInfo(@PathVariable String id) {
        log.info("id [{}]", id);
        return new User("tjs", "123");
    }*/


  /* @GetMapping("/")
    public List<User> query() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("t", "123"));
        users.add(new User("j", "123"));
        users.add(new User("s", "123"));
        return users;
    }*/

    @GetMapping("/")
    public List<User> query(@RequestParam(name = "username", required = false, defaultValue = "tjs")
                                    String nickname) {
        log.info("username [{}]", nickname);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("t", "123"));
        users.add(new User("j", "123"));
        users.add(new User("s", "123"));
        return users;
    }
/*
    @GetMapping("/")
    public List<User> query(UserQueryCondition condition) {
        log.info("condition [{}]", condition);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("t", "123"));
        users.add(new User("j", "123"));
        users.add(new User("s", "123"));
        return users;
    }*/

   /* @GetMapping("/user")
    public List<User> user(UserQueryCondition condition,
                           @PageableDefault(page = 2,size = 17, sort = "username,asc") Pageable pageable) {
        log.info("condition [{}]", condition);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("t", "123"));
        users.add(new User("j", "123"));
        users.add(new User("s", "123"));
        return users;
    }*/


}
