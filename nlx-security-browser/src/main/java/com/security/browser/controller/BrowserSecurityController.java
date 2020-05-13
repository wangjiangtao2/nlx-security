/*
 * 文件名：LoginController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.browser.controller;

import com.security.core.constants.SecurityConstants;
import com.security.core.properties.SecurityProperties;
import com.security.core.support.ResultBody;
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
 * @date 2020-05-13 9:59:59
 * @see BrowserSecurityController
 * @since JDK1.8
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时，跳转到这里
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultBody requireAuthentication(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是 [{}]", targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                //这里可以配置使用标准登录页还是使用自己写的登录页
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
                //redirectStrategy.sendRedirect(request, response, "https://www.baidu.com/");
                return null;
            }
        }
        return new ResultBody(401, "访问的服务需要身份验证，请引导用户到登录页", securityProperties.getBrowser().getLoginPage());
    }


    /**
     * 登录失败返回 401 以及提示信息.
     *
     * @return the rest
     */
    /*@PostMapping("/failure")
    public ResultBody loginFailure() {
        return ResultBody.failed(HttpStatus.UNAUTHORIZED.value(), "登录失败了，老哥");
    }*/

    /**
     * 登录成功后拿到个人信息.
     *
     * @return the rest
     */
   /* @PostMapping("/success")
    public ResultBody loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultBody.success(200, "登录成功", principal);
       *//* String username = principal.getUsername();
        //通过查询数据库获取用户信息
        SysUser sysUser = sysUserService.queryByUsername(username);
         sysUser.setEncodePassword("[PROTECT]");*//*
    }*/
}
