package icu.epq.user.controller;

import icu.epq.common.api.R;
import icu.epq.common.exception.WorldException;
import icu.epq.user.entity.User;
import icu.epq.user.service.IUserService;
import icu.epq.user.vo.UserVO;
import icu.epq.user.wrapper.UserWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息 控制器
 *
 * @author epqsky
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "用户信息", tags = "用户信息接口")
public class UserController {

    private final IUserService userService;

    @GetMapping("detail")
    @ApiOperation(value = "详情", notes = "传入id")
    public R<UserVO> detail(Integer id) {
        User detail = userService.getById(id);
        if (detail == null) {
            throw new WorldException(R.fail("用户信息不存在"));
        }
        return R.data(UserWrapper.build().entityVO(detail), "查询成功");
    }

}
