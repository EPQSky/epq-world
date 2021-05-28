package icu.epq.user.vo;

import icu.epq.user.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息视图实体类
 *
 * @author epqsky
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserVO", description = "用户信息")
public class UserVO extends User {

    private static final long serialVersionUID = 1L;

}
