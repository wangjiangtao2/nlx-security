/*
 * 文件名：DemoApplication.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-19 20:41:41
 * @see DemoApplication
 * @since JDK1.8
 */
@ComponentScan(basePackages = {"com.security.demo", "com.security.oauth2"})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
