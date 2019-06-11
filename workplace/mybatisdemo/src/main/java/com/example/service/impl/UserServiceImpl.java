package com.example.service.impl;


import com.example.beans.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById (Integer id){
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> getAllUser(){
        return userMapper.selectAllUser();
    }

    @Override
    public void addNewUser(String name, Integer age, Date date, String addr) {
        userMapper.insertUser(new User(name,age,date,addr));
    }

    @Override
    public void deleteUser(Integer id){
        userMapper.deleteUser(id);
    }

    @Override
    public void updateUser(User user){
        userMapper.updateUser(user);
    }
}
