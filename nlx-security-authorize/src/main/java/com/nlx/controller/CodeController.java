/*
 * 文件名：CodeController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-23 15:41:41
 * @see CodeController
 * @since JDK1.8
 */
@RestController
public class CodeController {

    @RequestMapping("/code/return")
    public Object get(HttpServletRequest request) {
        return request.getParameterMap();
    }
}
