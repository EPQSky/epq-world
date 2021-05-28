package icu.epq.user.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.epq.user.entity.User;
import icu.epq.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户信息 Feign 实现类
 *
 * @author epqsky
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class UserClient implements IUserClient {

    private final IUserService userService;

    @Override
    @GetMapping(API_PREFIX + "/getUserById")
    public User getUserById(Integer id) {
        return userService.getById(id);
    }

    @Override
    @GetMapping(API_PREFIX + "/getUserByAccount")
    public User getUserByAccount(String account) {
        return userService.getOne(new QueryWrapper<User>().lambda().eq(User::getAccount, account));
    }

}
