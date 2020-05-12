/*
 * 文件名：ValidateCodeProperties.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.properties;

import lombok.Data;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-12 21:51:51
 * @see ValidateCodeProperties
 * @since JDK1.8
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
}
