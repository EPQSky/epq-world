package icu.epq.user.wrapper;

import icu.epq.common.util.BeanUtil;
import icu.epq.user.entity.User;
import icu.epq.user.vo.UserVO;

/**
 * 用户信息包装类，返回视图层所需字段
 *
 * @author epqsky
 */
public class UserWrapper {

    public static UserWrapper build() {
        return new UserWrapper();
    }

    public UserVO entityVO(User user) {
        return BeanUtil.copyProperties(user, UserVO.class);
    }

}
