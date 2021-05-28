package icu.epq.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.epq.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息 Mapper 接口
 *
 * @author epqsky
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
