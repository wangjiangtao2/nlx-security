/*
 * 文件名：ClientDetailsService.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-23 14:14:14
 * @see ClientDetailsServiceImpl
 * @since JDK1.8
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 读取客户端信息，支持客户端模式、密码模式和授权码模式
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        // TODO 以后从redis中获取，再从数据库中获取
        /**clientId、权限范围 、授权模式、授权码模式返回code码的回调地址
         */
        BaseClientDetails clientDetails = new BaseClientDetails(clientId, "", "read, write",
                "client_credentials,password,authorization_code,refresh_token",
                "", "http://localhost:8080/code/return");

        clientDetails.setClientSecret(passwordEncoder.encode("123456"));
        // 自动授权，无需人工手动点击 approve
        clientDetails.setAutoApproveScopes(StringUtils.commaDelimitedListToSet("read, write"));
        clientDetails.setAccessTokenValiditySeconds(12000);
        clientDetails.setRefreshTokenValiditySeconds(500000);
        return clientDetails;
    }
}

/**
 * //7、 配置一个客户端，支持客户端模式、密码模式和授权码模式
 * clients.inMemory()  // 采用内存方式。也可以采用 数据库方式
 * .withClient("client1") // clientId
 * .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token") // 授权模式
 * .scopes("read") // 权限范围
 * .redirectUris("http://localhost:8091/login") // 授权码模式返回code码的回调地址
 * // 自动授权，无需人工手动点击 approve
 * .autoApprove(true)
 * .secret(passwordEncoder.encode("123456"))
 * .and()
 * .withClient("client2")
 * .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
 * .scopes("read")
 * .redirectUris("http://localhost:8092/login")
 * .autoApprove(true)
 * .secret(passwordEncoder.encode("123456"));
 */