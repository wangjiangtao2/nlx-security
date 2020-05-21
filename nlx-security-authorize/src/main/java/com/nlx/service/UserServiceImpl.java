/*
 * 文件名：UserServiceImpl.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-21 20:06:06
 * @see UserServiceImpl
 * @since JDK1.8
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, passwordEncoder.encode("123456"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
