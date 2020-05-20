/*
 * 文件名：DefaultAuthenticationFailedHandler.java
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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 登录失败处理器
 */
@Slf4j
@Component
public class DefaultAuthenticationFailedHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //ServletRequestUtils.getStringParameter(request, "username");
        //ServletRequestUtils.getStringParameter(request, "password");
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.warn("登录失败[{}]", objectMapper.writeValueAsString(parameterMap));
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(
                objectMapper.writeValueAsString(ResultBody.failed(401, exception.getMessage())));
    }
}
