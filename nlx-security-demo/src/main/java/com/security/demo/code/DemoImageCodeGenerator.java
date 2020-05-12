/*
 * 文件名：DemoImageCodeGenerator.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.code;

import com.security.core.validate.code.ImageCode;
import com.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-12 22:54:54
 * @see DemoImageCodeGenerator
 * @since JDK1.8
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator  implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
