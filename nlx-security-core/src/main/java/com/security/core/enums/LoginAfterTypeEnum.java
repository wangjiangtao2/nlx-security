/*
 * 文件名：LoginTypeEnum.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.enums;


/**
 * 登录成功或失败后 控制跳转行为的枚举类
 */
public enum LoginAfterTypeEnum {
    /**
     * json数据返回
     */
    JSON,
    /**
     * 重定向
     */
    REDIRECT;
}
