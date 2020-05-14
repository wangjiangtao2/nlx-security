/*
 * 文件名：ImageCodeGenerator.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code.image;

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.processor.ValidateCodeGenerator;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片验证码生成器
 */
@Data
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        /**
         * 验证码图片宽度 先从请求中获取验证码图片宽度，如果没有就从配置中获取
         */
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
                securityProperties.getCode().getImage().getWidth());
        /**
         * 验证码图片长度 先从请求中获取验证码图片长度，如果没有就从配置中获取
         */
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
                securityProperties.getCode().getImage().getHeight());
        /**
         * 验证码位数
         */
        int length = securityProperties.getCode().getImage().getLength();
        /**
         * 验证码有效时间
         */
        int expireIn = securityProperties.getCode().getImage().getExpireIn();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();

        graphics.setColor(getRandColor(200, 500));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        graphics.setColor(getRandColor(160, 200));
        //条纹
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        //生成随机数、然后写到图片上
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(rand, 13 * i + 6, 16);
        }

        graphics.dispose();

        return new ImageCode(image, sRand.toString(), expireIn);
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
