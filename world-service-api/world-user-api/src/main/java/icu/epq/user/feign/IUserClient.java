package icu.epq.user.feign;

import icu.epq.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户信息 Feign 接口类
 *
 * @author epqsky
 */
@FeignClient(
        value = "world-user",
        fallback = IUserClientFallback.class
)
public interface IUserClient {

    String API_PREFIX = "/feign";

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping(API_PREFIX + "/getUserById")
    User getUserById(@RequestParam(value = "id") Integer id);


    /**
     * 获取用户信息
     *
     * @param account
     * @return
     */
    @GetMapping(API_PREFIX + "/getUserByAccount")
    User getUserByAccount(@RequestParam(value = "account") String account);
}
