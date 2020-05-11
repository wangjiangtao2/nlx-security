/*
 * 文件名：IndexController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 9:37:37
 * @see IndexController
 * @since JDK1.8
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "demo security";
    }
}
