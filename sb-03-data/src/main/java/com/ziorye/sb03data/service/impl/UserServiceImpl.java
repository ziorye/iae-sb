package com.ziorye.sb03data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziorye.sb03data.bean.User;
import com.ziorye.sb03data.mapper.UserMapper;
import com.ziorye.sb03data.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
