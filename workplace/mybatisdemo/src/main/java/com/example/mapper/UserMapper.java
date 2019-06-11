package com.example.mapper;

import com.example.beans.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectUserById (int id);

    List<User> selectAllUser();

    void deleteUser(Integer id);

    void insertUser(User user);

    void updateUser(User user);
}
