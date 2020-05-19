/*
 * 文件名：UserServiceImpl.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 自定义用户登录实现
 */
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isBlank(username)) {
            log.error("username 为空");
            throw new UsernameNotFoundException("username用户名为空");
        }

        log.info("登录用户名 [{}]", username);

        /**
         * 1、根据用户名去数据库去查询用户信息；看用账号可用、账户过期、锁定(冻结)、密码过期等
         *  从数据库查询到的密码必须是经过加密的，而这个加密过程是在用户注册的时候进行加密的；
         *  这里模拟一个加密的数据库密码；这里直接从数据库读取密码就行了，不用再次加密
         */
        String password = passwordEncoder.encode("123456");
        log.info("密码 [{}]", password);
        //只需要将用户信息封装到UserDetails的实现类对象返回即可，框架会自行验证密码是否匹配
        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        /*return new User(username,
                password,
                true,    // 账号是否可用
                true,    // 账号是否过期
                true,    // 密码是否过期
                true,    // 账号是否被锁定
//权限
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
                */
        //TODO 做成数据库实现, 如果没有，会抛出UsernameNotFoundException异常
    }
}
