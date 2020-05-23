/*
 * 文件名：AuthorizationServerConfiguration.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.config;

import com.nlx.service.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings("all")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 客户端信息
     */
    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    /**
     * 1、引用 authenticationManager 支持 Password 授权模式
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 2、自动注入TokenStore所有实现类， 此项目配置了 redisTokenStore 和 jwtTokenStore
     */
    @Autowired
    private Map<String, TokenStore> tokenStoreMap = Collections.emptyMap();

    /**
     * 3、 jwt token的增强器
     */
    @Autowired(required = false)
    private AccessTokenConverter jwtAccessTokenConverter;

    /**
     * 4、由于存储策略时根据配置指定的，当使用redis策略时，tokenEnhancerChain 是没有被注入的，所以这里设置成 required = false
     */
    @Autowired(required = false)
    private TokenEnhancerChain tokenEnhancerChain; // 5、token的增强器链

    @Value("${spring.security.oauth2.storeType}")
    private String storeType = "jwt";  // 6、通过获取配置来判断当前使用哪种存储策略，默认jwt

    // token存储策略对象,JWT
    @Autowired
    private TokenStore jwtTokenStore;


    /**
     * 配置授权服务器端点的安全相关信息
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security// 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                //允许表单认证
                // 请求/oauth/token的，如果配置支持allowFormAuthenticationForClients的，
                // 且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置OAuth2的客户端相关信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 配置AuthorizationServerEndpointsConfigurer众多相关类，
     * 包括配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);

        // 设置token存储方式，这里提供redis和jwt
        endpoints.tokenStore(tokenStoreMap.get(storeType + "TokenStore"));
        if ("jwt".equalsIgnoreCase(storeType)) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter)
                    .tokenEnhancer(tokenEnhancerChain);
        }
    }
}