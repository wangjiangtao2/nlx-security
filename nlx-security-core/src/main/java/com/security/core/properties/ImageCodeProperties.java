/*
 * 文件名：ImageCodeProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;

import lombok.Data;

import java.util.Set;

/**
 * 图片验证码配置
 */
@Data
public class ImageCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 过期时间
     */
    private int expireIn = 60;
    /**
     * 验证码图片宽度
     */
    private int width = 67;
    /**
     * 验证码图片高度
     */
    private int height = 23;
    /**
     * 需要拦截的url
     */
    private Set<String> urls;
}
