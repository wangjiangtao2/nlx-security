/*
 * 文件名：ValidateCodeController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.security.core.constants.SecurityConstants;
import com.security.core.validate.code.sms.SmsCodeGenerator;
import com.security.core.validate.code.sms.SmsCodeSender;
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
 * 验证码相关类
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/code")
public class ValidateCodeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    @Qualifier("imageCodeGenerator")
    private ValidateCodeGenerator imageCodeGenerator;


    /**
     * 创建验证码
     */
    @GetMapping("/image")
    public void createImgCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //生成图片
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        //将随机数存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SecurityConstants.DEFAULT_SESSION_IMAGE_CODE_KEY, imageCode);
        //将生成的图片写到响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }


    /**
     * 短信验证码生成器
     */
    @Autowired
    private SmsCodeGenerator smsCodeGenerator;
    /**
     * 短信验证码发送器
     * {@link ValidateCodeBeanConfig#smsCodeSender()}
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 发送短信验证码
     */
    @GetMapping("/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //生成验证码
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        //将随机数存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SecurityConstants.DEFAULT_SESSION_SMS_CODE_KEY, smsCode);
        //获取手机号
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        //发送短信验证码
        smsCodeSender.send(mobile, smsCode.getCode());
    }


}
