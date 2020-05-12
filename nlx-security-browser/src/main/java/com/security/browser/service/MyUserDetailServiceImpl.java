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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名 [{}]", username);
        //根据用户名查询用户信息
        //根据查找的用户信息，看用户是否被冻结
        String password = passwordEncoder.encode("123456");
        log.info("密码 [{}]", password);
        //以后这里passwordEncoder.encode("123456")操作 是注册时候加密密码保存到数据库，这里直接从数据库读取密码就行了，不用再次加密
        User user = new User(username, password,
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
             //根据用户id获取用户角色
             List<String> roles = roleMapper.getUserRoleByUserId(user.getUserId());
             // 填充权限
             Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
             for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
             }
             //填充权限菜单
             List<Menu> menus=menuMapper.getRoleMenuByRoles(roles);
             redisTemplate.put("username", user);
             return new UserEntity(username, user.getPassword(), authorities,menus);

            return new org.springframework.security.core.userdetails.User(username,password, authorities);
         }
         //内存用户
        return User.withUsername(username)
                .password(passwordEncoder.encode("123456"))
                //权限
                .authorities("ROLE_ADMIN")
                .build();
        */
    }
}

