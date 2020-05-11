/*
 * 文件名：MockServer.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.security.demo.dto.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 10:51:51
 * @see MockServer
 * @since JDK1.8
 */
public class MockServer {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        // 配置WireMock服务器的地址 http://localhost:8080
        WireMock.configureFor("127.0.0.1", 8062);
        // 清除WireMock服务器里之前的配置
        WireMock.removeAllMappings();

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("t", "123"));
        users.add(new User("j", "456"));
        users.add(new User("s", "789"));
        String json = objectMapper.writeValueAsString(users);

        mock("/order/1", json);
    }

    private static void mock(String url, String content) throws IOException {
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)
                        .withHeader("t", "1")));
    }
}
