package com.vainglory.mapper;

import com.vainglory.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByUserName(@Param("username") String username);
    void add(User user);
}
