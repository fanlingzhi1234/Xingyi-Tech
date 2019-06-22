package com.xyzh.usermanager.service.impl;

import com.xyzh.usermanager.entity.User;
import com.xyzh.usermanager.mapper.UserMapper;
import com.xyzh.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
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
    public void addNewUser(User user) {
        userMapper.insertUser(user);
    }
    @Override
    public void addNewUser(String name, Integer age, String date, String addr) {
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
