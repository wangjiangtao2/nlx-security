/*
 * 文件名：SmsCodeAuthenticationProvider.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.authentication.mobile;

import lombok.Getter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-14 11:56:56
 * @see SmsCodeAuthenticationProvider
 * @since JDK1.8
 */
@Getter
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public SmsCodeAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 进行身份验证逻辑
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //在AuthencationManager里面挑一个provider来处理传进来token
        //看传进来的 authentication是不是SmsCodeAuthenticationToken类型的
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
