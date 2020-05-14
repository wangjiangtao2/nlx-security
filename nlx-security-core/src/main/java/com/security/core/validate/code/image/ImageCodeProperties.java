/*
 * 文件名：ImageCodeProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.image;

import lombok.Data;

import java.util.Set;

/**
 * 图片验证码配置
 */
@Data
public class ImageCodeProperties {
    /**
     * 验证码图片宽度
     */
    int width = 67;
    /**
     * 验证码图片长度
     */
    int height = 23;
    /**
     * 验证码位数
     */
    int length = 4;
    /**
     * 验证码有效时间 60s
     */
    int expireIn = 60;

    /**
     * 需要拦截的url
     */
    private Set<String> urls;
}
