/*
 * 文件名：DefaultLoginUrlAuthenticationEntryPoint.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.core.properties.SecurityPropertie;
import com.security.core.support.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 未登陆时返回 JSON 格式的数据给前端（否则为 html或者重定向）
 */
@Slf4j
@Component
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private SecurityPropertie securityPropertie;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.warn("uri = [{}] 未登录 [{}]", request.getRequestURI(),
                objectMapper.writeValueAsString(parameterMap));
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        //返回json处理 默认也是json处理
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ResultBody.failed(401, "未登录, 请先登录", securityPropertie.getApp().getLoginPage())));
    }
}
