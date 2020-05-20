/*
 * 文件名：DefaultAuthenticationSuccessHandler.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.core.support.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 * {@link SavedRequestAwareAuthenticationSuccessHandler}是Spring Security默认的成功处理器
 */
@Slf4j
@Component
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        log.info("登录成功");
        //返回json处理 默认也是json处理
        response.setContentType("application/json;charset=UTF-8");
        log.info("认证信息: [{}]", objectMapper.writeValueAsString(authentication));
        String result = objectMapper.writeValueAsString(
                ResultBody.success(100, "登录成功", authentication));
        response.getWriter().write(result);
    }
}
