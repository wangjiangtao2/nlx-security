/*
 * 文件名：ImageValidateCodeProcessor.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.image;

import com.security.core.validate.code.AbstractValidateCodeProcessor;
import com.security.core.validate.code.ValidateCode;
import com.security.core.validate.code.ValidateCodeGenerator;
import com.security.core.validate.code.ValidateCodeStore;
import com.security.core.validate.code.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 图片验证码处理器
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor implements InitializingBean {

    /**
     * 验证码生成器
     */
    @Autowired
    @Qualifier("imageValidateCodeGenerator")
    private ValidateCodeGenerator validateCodeGenerator;

    /**
     * 验证码保存
     */
    @Autowired
    private ValidateCodeStore validateCodeStore;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(validateCodeGenerator, "ImageValidateCodeProcessor中的validateCodeGenerator 不能为空");
        super.setValidateCodeGenerator(validateCodeGenerator);
        super.setValidateCodeStore(validateCodeStore);
    }

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        ImageValidateCode code = (ImageValidateCode) validateCode;
        // 没有缓存
        request.getResponse().setHeader("Cache-Control", "no-store, no-cache");
        request.getResponse().setContentType("image/jpeg");

        ImageIO.write(code.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

    @Override
    public boolean supports(String type) {
        return StringUtils.equalsIgnoreCase(ValidateCodeType.IMAGE.name(), type);
    }
}
