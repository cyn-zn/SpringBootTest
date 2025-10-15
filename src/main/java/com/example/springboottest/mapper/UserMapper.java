package com.example.springboottest.mapper;

import com.example.springboottest.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT id, username, password, type FROM user WHERE username = #{username}")
    User findByUsername(String username);
}
