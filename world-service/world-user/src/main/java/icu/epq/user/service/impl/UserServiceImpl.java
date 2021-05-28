package icu.epq.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.epq.user.entity.User;
import icu.epq.user.mapper.UserMapper;
import icu.epq.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * User 服务实现类
 *
 * @author epqsky
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
