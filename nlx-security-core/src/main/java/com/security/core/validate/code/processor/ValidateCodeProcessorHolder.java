/*
 * 文件名：ValidateCodeProcessorHolder.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.processor;

import com.security.core.validate.code.ValidateCodeType;
import com.security.core.validate.code.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码
 */
@Component
public class ValidateCodeProcessorHolder {

    /**
     * 收集系统中所有 {@link ValidateCodeProcessor} 接口的实现。
     * 启动的时候，会查找Spring容器里面所有 ValidateCodeProcessor接口的实现，以bean的名字作为key，放到map中去
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 根据验证码类型获取不同的处理器
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

    /**
     * 根据验证码类型获取不同的处理器
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }
}
