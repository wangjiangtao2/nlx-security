/*
 * 文件名：ValidateCodeFilter.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.security.core.constants.SecurityConstants;
import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.img.ImageValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
 * OncePerRequestFilter 过滤器只会调用一次
 *
 * @author tjs
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private AuthenticationFailureHandler failedHandler;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 需要拦截的地址集合
     */
    private Set<String> urls = new HashSet<>();
    /**
     * 路径匹配工具类
     */
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // 默认放行
        boolean flag = false;
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                flag = true;
            }
        }

        if (flag) {
            log.info("验证码验证请求路径:[{}]", request.getRequestURI());
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                //失败调用我们的自定义失败处理器
                failedHandler.onAuthenticationFailure(request, response, e);
                //后续流程终止
                return;
            }
        }
        //后续过滤器继续执行
        chain.doFilter(request, response);
    }

    /**
     * 图片验证码校验
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 拿到之前存储的imageCode信息
        ImageValidateCode codeInSession = (ImageValidateCode) sessionStrategy.getAttribute(request, SecurityConstants.DEFAULT_IMAGE_VALIDATE_CODE_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.expired()) {
            sessionStrategy.removeAttribute(request, SecurityConstants.DEFAULT_IMAGE_VALIDATE_CODE_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        //验证通过 移除缓存
        sessionStrategy.removeAttribute(request, SecurityConstants.DEFAULT_IMAGE_VALIDATE_CODE_KEY);
    }
}
