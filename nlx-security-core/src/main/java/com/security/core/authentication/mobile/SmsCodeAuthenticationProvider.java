/*
 * 文件名：SmsCodeAuthenticationProvider.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.authentication.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

/**
 * 手机号登录模式 Provider校验逻辑类
 * 参考
 * {@link DaoAuthenticationProvider}
 * {@link AbstractUserDetailsAuthenticationProvider}
 */
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Getter
    @Setter
    private UserDetailsService userDetailsService;

    public SmsCodeAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * AuthenticationManager 验证该Provider是否支持 认证
     */
    @Override
    public boolean supports(Class<?> authentication) {
        //判断authentication是不是SmsCodeAuthenticationToken类型的
        return authentication.isAssignableFrom(SmsCodeAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(SmsCodeAuthenticationToken.class, authentication,
                "SmsAuthenticationProvider.onlySupports Only SmsAuthenticationToken is supported");

        SmsCodeAuthenticationToken smsAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        Assert.notNull(authentication.getPrincipal(), "SmsAuthenticationProvider 手机号不能为空");
        UserDetails loadedUser = null;
        try {
            //TODO 在自定义userDetailsService添加loadUserByMobile方法
            loadedUser = this.userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        Assert.notNull(loadedUser,
                "根据手机号" + authentication.getPrincipal() + "获取的用户信息为空");

        //校验 账号过期、账号是否可用、账号是否锁定
        userCheck(loadedUser);

        //认证通过
        SmsCodeAuthenticationToken authenticationTokenResult = new SmsCodeAuthenticationToken(loadedUser, loadedUser.getAuthorities());
        //将之前未认证的请求放进认证后的Token中
        authenticationTokenResult.setDetails(smsAuthenticationToken.getDetails());
        return authenticationTokenResult;
    }

    private void userCheck(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            log.debug("User account is locked");
            throw new LockedException("SmsAuthenticationProvider.locked; User account is locked");
        }
        if (!user.isEnabled()) {
            log.debug("User account is disabled");
            throw new LockedException("SmsAuthenticationProvider.disabled; User is disabled");
        }

        if (!user.isAccountNonExpired()) {
            log.debug("User account is expired");
            throw new LockedException("SmsAuthenticationProvider.expired; User account has expired");
        }
    }
}

