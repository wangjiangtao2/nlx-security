/*
 * 文件名：UserControllerTest1.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.demo.DemoApplication;
import com.security.demo.dto.User;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-11 10:11:11
 * @see UserControllerTest1
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest1 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

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
                .andExpect(MockMvcResultMatchers.status().isOk())
                //返回集合数量是3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void createTest() throws Exception {
        String json = "{\"name\":\"Mock测试\",\"classHour\":\"4课时\",\"id\":\"rn:practice:Course:5af27fa5d34f435e581e5bbf\"}";
        //将json格式字符串转换成Course对象里的属性值
        User user = mapper.readValue(json, User.class);
        //perform,执行一个RequestBuilders请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理
        mockMvc.perform(
                //构造一个post请求
                MockMvcRequestBuilders.post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        //使用writeValueAsString()方法来获取对象的JSON字符串表示
                        .content(mapper.writeValueAsString(user)))
                //andExpect，添加ResultMathcers验证规则，验证控制器执行完成后结果是否正确，【这是一个断言】
                .andExpect(MockMvcResultMatchers.status().is(200))

                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))

                //假定返回的结果中，"name" 值为 "Mock测试2",如果不是的话，会抛出异常java.lang.AssertionError，并给出期望值和实际值
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mock测试2"))

                //添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
                .andDo(print())

                //返回相应的MvcResult
                .andReturn();
    }
}
