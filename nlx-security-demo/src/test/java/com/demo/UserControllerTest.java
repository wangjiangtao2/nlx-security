/*
 * 文件名：UserControllerTest.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.demo;

import com.security.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String content = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                //返回状态是ok
                .andExpect(status().isOk())
                //"username" 值为 "tjs",如果不是的话，会抛出异常java.lang.AssertionError，并给出期望值和实际值
                .andExpect(jsonPath("$.username").value("tjs"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }
    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/user/a")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                //返回状态是ok
                .andExpect(status().is4xxClientError());
    }



    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/users/")
                        //请求参数
                        .param("username", "tjs")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                //返回状态是ok
                .andExpect(status().isOk())
                //返回集合数量是3
                .andExpect(jsonPath("$.length()").value(3))
                //添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
                .andDo(print());
    }
}
