/*
 * 文件名：MyUserDetailServiceImpl.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.browser.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 15:38:38
 * @see MyUserDetailServiceImpl
 * @since JDK1.8
 */
@Slf4j
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println( passwordEncoder.encode("123456"));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名 [{}]", username);
        //根据用户名查询用户信息
        //根据查找的用户信息，看用户是否被冻结
        String password = passwordEncoder.encode("123456");
        log.info("密码 [{}]", password);
        //以后这里passwordEncoder.encode("123456")操作 是注册时候加密密码保存到数据库，这里直接从数据库读取密码就行了，不用再次加密
        User user = new User(username, passwordEncoder.encode("123456"),
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        //new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return user;
        /*
        //先从redis中获取
         UserDetails user = redisTemplate.getUserFromCache(username);
         if (user == null) {
             //查询数据库操作
             user = userMapper.selectByName(username);
             if(user == null ){
                throw  new UsernameNotFoundException("the user is not found");
             }
             // 从数据库中 查询用户角色编码
            String role = "ROLE_ADMIN";
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            // 线上环境应该通过用户名查询数据库获取加密后的密码
            String password = passwordEncoder.encode("123456");
            redisTemplate.put("key", user);
            return new org.springframework.security.core.userdetails.User(username,password, authorities);
         }
         //以后这里先通过数据库获取，保存到redis中
        return User.withUsername(username)
                .password(passwordEncoder.encode("123456"))
                //权限
                .authorities("ROLE_ADMIN")
                .build();
        */
    }
}
