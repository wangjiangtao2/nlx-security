/*
 * 文件名：BrowserSecurityController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.browser.controller;

import com.security.browser.support.ResultBody;
import com.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 23:57:57
 * @see BrowserSecurityController
 * @since JDK1.8
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired(required = false)
    private SecurityProperties securityProperties;


    /**
     * 当需要身份认证时，跳转到这里
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultBody requireAuthentication1(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是 [{}]", targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                //这里可以配置使用标准登录页还是使用自己写的登录页
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new ResultBody("401", "访问的服务需要身份验证，请引导用户到登录页", securityProperties.getBrowser().getLoginPage());
//        return new SimpleResponse("访问的服务需要身份验证，请引导用户到登录页," + securityProperties.getBrowser().getLoginPage());
    }
}
