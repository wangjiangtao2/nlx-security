/*
 * 文件名：LoginRequireController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.controller;

import com.security.core.constants.SecurityConstants;
import com.security.core.properties.SecurityPropertie;
import com.security.core.support.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录认证控制器
 */
@RestController
@Slf4j
public class LoginRequireController {

    @Autowired
    private SecurityPropertie securityPropertie;

    /**
     * 当需要进行身份认证的时候跳转到此方法
     * @return 将信息以JSON形式返回给前端
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultBody requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("LoginRequireController: uri = [{}], RemoteAddr = [{}]", request.getRequestURI(), request.getRemoteAddr());
        return ResultBody.failed(401, "访问的服务需要身份认证，请引导用户到登录页面",
                securityPropertie.getApp().getLoginPage());
    }
}
