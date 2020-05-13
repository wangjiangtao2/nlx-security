/*
 * 文件名：ValidateCode.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 验证码实体
 */
@Data
public class ValidateCode {
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 是否过期,过期返回true
     */
    public boolean expired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
