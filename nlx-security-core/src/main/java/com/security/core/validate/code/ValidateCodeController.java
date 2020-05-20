/*
 * 文件名：ValidateCodeController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码控制类
 */
@RestController
@RequestMapping("/code")
public class ValidateCodeController {

    @Autowired
    private ProcessManager processManager;

    @GetMapping("/{type}")
    public void code(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ValidateCodeProcessor validateCodeProcessor = processManager.findValidateCodeProcessor(type);
        validateCodeProcessor.create(new ServletWebRequest(request, response));
    }

    @GetMapping("/verify/{type}")
    public void verify(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ValidateCodeProcessor processor = processManager.findValidateCodeProcessor(type);
        processor.validate(new ServletWebRequest(request, response));
    }
}
