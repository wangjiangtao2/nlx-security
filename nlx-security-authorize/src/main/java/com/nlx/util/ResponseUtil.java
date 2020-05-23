/*
 * 文件名：ResponseUtil.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.nlx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nlx.support.ResultBody;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Jasmine
 * @version 1.0
 * @date 2020-05-23 13:03:03
 * @see ResponseUtil
 * @since JDK1.8
 */
public class ResponseUtil {

    /**
     * 通过流写到前端
     *
     * @param response
     * @param code     返回状态码
     * @param msg      返回信息
     */
    public static void responseWriter(HttpServletResponse response, int code, String msg) throws IOException {
        ResultBody result = ResultBody.success(code, msg, null);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        try (
                Writer writer = response.getWriter()
        ) {
            writer.write(JSON.toJSONString(
                    result, SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.PrettyFormat));
            writer.flush();
        }
    }
}
