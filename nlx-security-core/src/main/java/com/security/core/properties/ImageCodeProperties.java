/*
 * 文件名：ImageCodeProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;

import lombok.Data;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-12 21:50:50
 * @see ImageCodeProperties
 * @since JDK1.8
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
     * 逗号隔开的url
     */
    private String url;
}
