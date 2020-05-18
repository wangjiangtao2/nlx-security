/*
 * 文件名：ValidateCodeController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.security.core.constants.SecurityConstants;
import com.security.core.validate.code.img.ImageValidateCode;
import com.security.core.validate.code.sms.SmsValidateCodeGenerator;
import com.security.core.validate.code.sms.SmsValidateCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码控制器
 */
@RestController
@RequestMapping("/code")
public class ValidateCodeController {

    /**
     * spring session 工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 图片验证码生成器
     */
    @Autowired
    @Qualifier("imageValidateCodeGenerator")
    private ValidateCodeGenerator imageValidateCodeGenerator;

    /**
     * 创建验证码
     */
    @GetMapping("/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //1、生成图片验证码
        ImageValidateCode imageCode = (ImageValidateCode) imageValidateCodeGenerator.generate(new ServletWebRequest(request));
        //2、将图片验证码存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SecurityConstants.DEFAULT_IMAGE_VALIDATE_CODE_KEY, imageCode);
        //3.将图片验证码写到响应流中
        // 没有缓存
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }


    /**
     * 短信验证码生成器
     */
    @Autowired
    private SmsValidateCodeGenerator smsValidateCodeGenerator;
    /**
     * 短信验证码发送器
     * {@link ValidateCodeBeanConfig#smsCodeSender()}
     */
    @Autowired
    private SmsValidateCodeSender smsValidateCodeSender;

    /**
     * 发送短信验证码
     */
    @GetMapping("/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //1、生成短信验证码
        ValidateCode smsCode = smsValidateCodeGenerator.generate(new ServletWebRequest(request));
        //2、将短信验证码存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SecurityConstants.DEFAULT_SMS_VALIDATE_CODE_KEY, smsCode);
        //3、发送短信验证码
        //获取手机号
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsValidateCodeSender.send(mobile, smsCode.getCode());
    }


}
