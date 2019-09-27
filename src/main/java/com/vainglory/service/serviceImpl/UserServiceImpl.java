package com.vainglory.service.serviceImpl;

import com.vainglory.mapper.UserMapper;
import com.vainglory.pojo.User;
import com.vainglory.service.IUserService;
import com.vainglory.util.CodeUtils;
import com.vainglory.util.EmailUtils;
import com.vainglory.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl日志：register...");
        user.setFlag(0);
        user.setRole(1);
        user.setCode(CodeUtils.getCode());
        user.setPassword(MD5Util.encode(user.getPassword()));
        userMapper.add(user);
        EmailUtils.sendEmail(user);
    }
}
