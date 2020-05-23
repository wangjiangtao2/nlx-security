/*
 * 文件名：UserController.java
 * 版权：Copyright by www.newlixon.com/
 * 描述：
 * 修改人： Jasmine
 * 修改时间：2020年05月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.security.demo.controller;

/**
 * 用户控制器
 */
/*
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public ResultBody index() {
        redisTemplate.opsForValue().set("1", "2");
        Object o = redisTemplate.opsForValue().get("1");
        return ResultBody.success(200, "查询成功", o);
    }

    @Autowired
    private ObjectMapper objectMapper;

    */
/**
     * 认证主体信息
     *//*

    @GetMapping("/me")
    public ResultBody getCurrentUser(HttpServletResponse response, @AuthenticationPrincipal UserDetails user) throws IOException {
        System.out.println("获取用户信息 " + objectMapper.writeValueAsString(user));
        log.info("获取用户信息 [{}]", user);
        return ResultBody.success(100, "登录成功", user);

        //返回json处理 默认也是json处理
       */
/* response.setContentType("application/json;charset=UTF-8");
        log.info("认证信息: [{}]", objectMapper.writeValueAsString(user));
        String result = objectMapper.writeValueAsString(
                ResultBody.success(100, "登录成功", user));
        response.getWriter().write(result);
        return user;*//*

    }

    */
/**
     * 获取用户认证信息
     *//*

    @GetMapping("/authentication")
    public Authentication getCurrentAuthentication() {
        log.info("获取用户信息 [{}]", SecurityContextHolder.getContext().getAuthentication());
        return SecurityContextHolder.getContext().getAuthentication();
    }

    */
/**
     * 获取用户认证信息
     * 同getCurrentAuthentication spring 会帮我们注入
     *//*

    @GetMapping("/authentication/auto")
    public Authentication getCurrentAuthentication2(Authentication authentication) {
        log.info("获取用户信息 [{}]", authentication);
        return authentication;
    }

}
*/
