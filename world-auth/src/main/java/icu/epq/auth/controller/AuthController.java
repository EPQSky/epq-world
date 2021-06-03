package icu.epq.auth.controller;

import icu.epq.common.api.R;
import icu.epq.common.exception.WorldException;
import icu.epq.common.granter.BaseGranter;
import icu.epq.common.util.JwtUtil;
import icu.epq.log.annotation.ActionLog;
import icu.epq.log.annotation.ApiLog;
import icu.epq.user.entity.User;
import icu.epq.user.feign.IUserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户授权认证 控制器
 *
 * @author epqsky
 */
@RestController
@AllArgsConstructor
@Api(value = "用户授权认证", tags = "授权接口")
public class AuthController {

    private final IUserClient userClient;

    @PostMapping("/token")
    @ApiLog
    @ActionLog
    @ApiOperation(value = "获取认证token", notes = "输入账号、密码")
    public R<BaseGranter> token(@RequestParam String account, @RequestParam String password) {
        User user = userClient.getUserByAccount(account);
        if (user == null) {
            throw new WorldException(R.fail("用户不存在"));
        }
        if (!password.equals(user.getPassword())) {
            throw new WorldException(R.fail("密码错误"));
        }

        BaseGranter granter = new BaseGranter("local", user.getName(), user.getUserCode(), user.getEmail(), user.getStatus(), null);
        JwtUtil.createAccessToken(granter);

        return R.data(granter, "登陆成功");
    }

}
