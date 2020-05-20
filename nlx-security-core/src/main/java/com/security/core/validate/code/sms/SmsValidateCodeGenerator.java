/*
 * 文件名：SmsValidateCodeGenerator.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.sms;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-20 17:39:39
 * @see SmsValidateCodeGenerator
 * @since JDK1.8
 */

import com.security.core.validate.code.ValidateCode;
import com.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 */
@Data
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(6);
        return new ValidateCode(code, 3600);
    }
}
