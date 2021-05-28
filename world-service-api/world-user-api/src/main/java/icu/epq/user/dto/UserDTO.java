package icu.epq.user.dto;

import icu.epq.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息数据传输对象实体类
 *
 * @author epqsky
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends User {

    private static final long serialVersionUID = 1L;

}
