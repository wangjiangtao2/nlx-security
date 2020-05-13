/*
 * 文件名：ValidateCodeFilter.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.security.core.constants.SecurityConstants;
import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.exception.ValidateCodeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 验证码过滤器
 */
@Slf4j
@Component
@Data
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationFailureHandler failedHandler;

    /**
     * 需要拦截的地址集合
     */
    private Set<String> urls = new HashSet<>();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        //登录请求一定做验证码
        urls.add(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM);
        //获取配置文件中需要拦截的url
        Set<String> url = securityProperties.getCode().getImage().getUrls();
        if (CollectionUtils.isNotEmpty(url)) {
            urls.addAll(url);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        boolean flag = false;
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                flag =true;
            }
        }

        if(flag) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                failedHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, SecurityConstants.DEFAULT_SESSION_IMAGE_CODE_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.expired()) {
            sessionStrategy.removeAttribute(request, SecurityConstants.DEFAULT_SESSION_IMAGE_CODE_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, SecurityConstants.DEFAULT_SESSION_IMAGE_CODE_KEY);
    }
}
