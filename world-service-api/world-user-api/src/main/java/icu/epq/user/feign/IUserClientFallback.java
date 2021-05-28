package icu.epq.user.feign;

import icu.epq.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用户信息 Feign 失败配置
 *
 * @author epqsky
 */
@Component
public class IUserClientFallback implements IUserClient {

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public User getUserByAccount(String account) {
        return null;
    }

}
