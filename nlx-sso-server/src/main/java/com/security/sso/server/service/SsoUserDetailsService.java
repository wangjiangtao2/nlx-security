/*
 * 文件名：SsoUserDetailsService.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.sso.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-16 16:02:02
 * @see SsoUserDetailsService
 * @since JDK1.8
 */
@Component
@Slf4j
public class SsoUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        //TODO 这里实际是查询数据库获取
        log.info("用户名 {}，数据库密码{}", username, password);
        User admin = new User(username,
//                              "{noop}123456",
                password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
        return admin;
    }
}
