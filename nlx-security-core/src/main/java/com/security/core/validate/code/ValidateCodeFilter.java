/*
 * 文件名：ValidateCodeFilter.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;


/**
 * 验证码过滤器
 * OncePerRequestFilter 过滤器只会调用一次
 */

import com.security.core.properties.SecurityPropertie;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 验证码过滤器
 */
@Slf4j
@Component
@Data
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityPropertie securityPropertie;

    /**
     * 失败处理类
     */
    @Autowired
    private AuthenticationFailureHandler failedHandler;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ProcessManager processManager;

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * String 需要校验验证码的url
     * ValidateCodeType 标记 是图片验证码还是短信验证码
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        //从配置文件中获取需要 拦截图片验证码的的url
        Set<String> imageUrls = securityPropertie.getCode().getImage().getUrls();
        //从配置文件中获取需要 拦截短信验证码的的url
        Set<String> smsUrls = securityPropertie.getCode().getSms().getUrls();

        //将系统中配置的需要校验验证码的URL根据校验的类型放入map
        if (!CollectionUtils.isEmpty(imageUrls)) {
            imageUrls.forEach(url -> urlMap.put(url, ValidateCodeType.IMAGE));
        }
        if (!CollectionUtils.isEmpty(smsUrls)) {
            smsUrls.forEach(url -> urlMap.put(url, ValidateCodeType.SMS));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 校验当前请求是否需要校验 及 获取验证码类型
        ValidateCodeType type = getValidateCodeType(request);

        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                processManager.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException e) {
                failedHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        //因为GET请求不需要校验验证码
        if (HttpMethod.GET.name().equals(request.getMethod())) {
            return null;
        }

        Set<Map.Entry<String, ValidateCodeType>> entries = urlMap.entrySet();
        for (Map.Entry<String, ValidateCodeType> entry : entries) {
            if (pathMatcher.match(entry.getKey(), request.getRequestURI())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
