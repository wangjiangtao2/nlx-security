/*
 * 文件名：UserDetailServiceImpl.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.service;

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
 * 用户信息获取(比如从数据库获取用户信息)
 *
 * @author Administrator
 */
@Slf4j
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名 [{}]", username);
        //根据用户名查询用户信息，看用账号可用、账户过期、锁定(冻结)、密码过期等
        //以后这里passwordEncoder.encode("123456")操作 是注册时候加密密码保存到数据库，这里直接从数据库读取密码就行了，不用再次加密
        String password = passwordEncoder.encode("123456");
        log.info("密码 [{}]", password);
        //只需要将用户信息封装到UserDetails的实现类对象返回即可，框架会自行验证密码是否匹配
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        /*return new User(username,
                password,
                true,    // 账号是否可用
                true,    // 账号是否过期
                true,    // 密码是否过期
                true,    // 账号是否被锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));*/
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
             List<Menu> menus = menuMapper.getRoleMenuByRoles(roles);
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
