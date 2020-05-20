/*
 * 文件名：ProcessManager.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 验证码处理器管理类
 */
@Slf4j
@Component
public class ProcessManager implements CommandLineRunner {
    /**
     * 容器上下文
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 所有验证码处理器 ValidateCodeProcessor的实现类
     */
    private List<ValidateCodeProcessor> processors = Collections.emptyList();

    @Override
    public void run(String... args) throws Exception {
        //获取ValidateCodeProcessor 的所有实现类,并保存起来
        Map<String, ValidateCodeProcessor> map = applicationContext.getBeansOfType(ValidateCodeProcessor.class);
        processors = map.values()
                .stream()
                .collect(Collectors.toList());

    }

    /**
     * 根据验证码类型获取不同的处理器
     * {@link ValidateCodeType}
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        ValidateCodeProcessor getProcessor = null;
        for (ValidateCodeProcessor processor : processors) {
            //根据传入类型，判断是否支持
            if (processor.supports(type)) {
                getProcessor = processor;
                break;
            }
        }
        if (getProcessor == null) {
            throw new IllegalArgumentException("验证码处理器 [" + type + "] 不存在");
        }
        return getProcessor;
    }

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }
}
