/*
 * 文件名：AbstractValidateCodeProcessor.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.core.support.ResultBody;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码抽象处理器
 */
@Getter
@Setter
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    /**
     * 验证码生成器
     */
    private ValidateCodeGenerator validateCodeGenerator;

    /**
     * 保存验证码
     */
    private ValidateCodeStore validateCodeStore;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        //1、生成验证码
        ValidateCode code = validateCodeGenerator.generate(request);
        //2、保存验证码
        save(request, code);
        //3、发送验证码（图片响应流写回，短信调用渠道直接发送）
        send(request, code);

        String result = objectMapper.writeValueAsString(
                ResultBody.success(100, "发送成功"));
        request.getResponse().getWriter().write(result);
    }

    /**
     * 发送校验码，由子类实现
     */
    protected abstract void send(ServletWebRequest request, ValidateCode validateCode) throws Exception;

    /**
     * 保存校验码
     */
    private void save(ServletWebRequest request, ValidateCode validateCode) {
        validateCodeStore.save(request, validateCode, getValidateCodeType());
    }


    @Override
    public void validate(ServletWebRequest request) {
        //验证码类型
        ValidateCodeType codeType = getValidateCodeType();
        //从存储中获取 验证码
        ValidateCode codeInSession = validateCodeStore.get(request, codeType);

        String codeInRequest;
        //验证码类型
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.expired()) {
            validateCodeStore.remove(request, getValidateCodeType());
            throw new ValidateCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不匹配");
        }
        //移除验证码code
        validateCodeStore.remove(request, codeType);
    }

    /**
     * 获取验证码类型
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
        ValidateCodeType validateCodeType = ValidateCodeType.valueOf(type.toUpperCase());
        if (validateCodeType == null) {
            throw new ValidateCodeException("type 不存在");
        }
        return validateCodeType;
    }
}
