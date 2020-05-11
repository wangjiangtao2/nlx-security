/*
 * 文件名：UserQueryCondition.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.dto;

import lombok.Data;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 9:45:45
 * @see UserQueryCondition
 * @since JDK1.8
 */
@Data
public class UserQueryCondition {
    private String username;
    private int age;
    private int ageTo;
    private String xxx;
}
