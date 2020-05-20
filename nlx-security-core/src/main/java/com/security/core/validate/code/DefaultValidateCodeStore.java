/*
 * 文件名：DefaultValidateCodeStore.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.core.validate.code;

import com.alibaba.fastjson.JSONObject;
import com.security.core.constants.SecurityConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * 默认存储验证码 类
 */
public class DefaultValidateCodeStore implements ValidateCodeStore {

    /**
     * 验证码放入redis key 规则：VALIDATE_CODE_{TYPE}_{DEVICE_ID}
     * eg: VALIDATE_CODE_IMAGE_122222222233
     */
    private final static String CODE_KEY_PATTERN = "VALIDATE_CODE_%s_%s";

    private RedisTemplate redisTemplate;

    public DefaultValidateCodeStore(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        String key = buildKey(request, validateCodeType);
        String value = JSONObject.toJSONString(code);
        redisTemplate.opsForValue().set(key, value, 15, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String key = buildKey(request, validateCodeType);
        String value = (String) redisTemplate.opsForValue().get(key);
        ValidateCode code = JSONObject.parseObject(value, ValidateCode.class);
        return code;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request, validateCodeType));
    }

    /**
     * 构建验证码放入redis时的key; 在保存的时候也使用该key
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceId = request.getHeader(SecurityConstants.HEAD_NAME_DEVICE_ID);
      /*  if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }*/
        return String.format(CODE_KEY_PATTERN, validateCodeType, deviceId);
    }
}
