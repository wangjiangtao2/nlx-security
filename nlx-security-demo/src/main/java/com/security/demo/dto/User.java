/*
 * 文件名：User.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 9:43:43
 * @see User
 * @since JDK1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public interface UserSimpleView{};

    public interface UserDetailView extends UserSimpleView{};

    @JsonView({UserSimpleView.class})
    private String username;

    @JsonView({UserDetailView.class})
    private String password;
}

