package icu.epq.auth.controller;

import icu.epq.user.entity.User;
import icu.epq.user.feign.IUserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author epqsky
 */
@Slf4j
@RefreshScope
@RestController
@Api(value = "测试", tags = "测试接口")
public class HelloController {

    private final IUserClient userClient;

    @Value("${epq}")
    private String epq;

    public HelloController(IUserClient userClient) {
        this.userClient = userClient;
    }

    @ApiOperation(value = "测试", notes = "获取配置文件中的epq属性")
    @GetMapping("/hello")
    public String hello() {
        log.info("epq:" + epq);
        return epq;
    }

    @ApiOperation(value = "测试", notes = "获取用户信息")
    @GetMapping("/getUser")
    public User getUser(Integer id) {
        User user = userClient.getUserById(id);
        log.info(user.toString());
        return user;
    }

}
