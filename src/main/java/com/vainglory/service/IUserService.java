package com.vainglory.service;

import com.vainglory.pojo.User;
import org.springframework.stereotype.Service;

public interface IUserService {
    User checkUserName(String username);
    void register(User user);
}
