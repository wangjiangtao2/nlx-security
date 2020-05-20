/*
 * 文件名：SmsValidateCodeProcessor.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.sms;

import com.security.core.constants.SecurityConstants;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor implements InitializingBean {

    /**
     * 验证码生成器
     */
    @Autowired
    @Qualifier("smsValidateCodeGenerator")
    private ValidateCodeGenerator validateCodeGenerator;

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsValidateCodeSender smsCodeSender;

    /**
     * 验证码保存
     */
    @Autowired
    private ValidateCodeStore validateCodeStore;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(validateCodeGenerator, "SmsValidateCodeProcessor中的validateCodeGenerator不能为空");
        super.setValidateCodeGenerator(validateCodeGenerator);
        super.setValidateCodeStore(validateCodeStore);
    }

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        //从请求参数中获取手机号
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstants.PARAMETER_NAME_MOBILE);
        //调用发送器发送号码
        smsCodeSender.send(mobile, validateCode.getCode());

    }

    @Override
    public boolean supports(String type) {
        return StringUtils.equalsIgnoreCase(ValidateCodeType.SMS.name(), type);
    }
}
