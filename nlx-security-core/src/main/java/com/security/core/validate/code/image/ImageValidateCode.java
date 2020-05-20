/*
 * 文件名：ImageValidateCode.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.image;

import com.security.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * 图片验证码信息对象
 */
@Data
public class ImageValidateCode extends ValidateCode {

    /**
     * 图片
     */
    private BufferedImage image;

    public ImageValidateCode() {
    }

    public ImageValidateCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}
