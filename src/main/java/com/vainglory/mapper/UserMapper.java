package com.vainglory.mapper;

import com.vainglory.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User findByUserName(@Param("username") String username);
    void add(User user);
    List<User> findByFlag(Integer flag);
}
