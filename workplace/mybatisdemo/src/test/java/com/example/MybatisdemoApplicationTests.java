package com.example;

import com.example.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import com.example.beans.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisdemoApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void selectUserById() {
        User user = userMapper.selectUserById(1);
        System.out.println(user);
    }
    @Test
    public void selectAllUser(){
        List<User> userlist = userMapper.selectAllUser();
        System.out.println(userlist);
    }

    @Test
    @Transactional
    public void insertUser(){

        Date date = new Date();
        System.out.println(date);
        User user = new User("ddd",19, date,"beijing");
        System.out.println(user);
        userMapper.insertUser(user);
        List<User> userlist = userMapper.selectAllUser();
        System.out.println(userlist);
    }

    @Test
    @Transactional
    public void deleteUser(){
        System.out.println(userMapper.selectUserById(3));
        try{
            userMapper.deleteUser(3);
            System.out.println("delete successfully");
            System.out.println(userMapper.selectUserById(3));
        }catch (Exception e){
            throw e;
        }
    }

    @Test
    @Transactional
    public void updateUser(){
        System.out.println(userMapper.selectUserById(3));
        User user = userMapper.selectUserById(3);
        user.setAddr("changed addr");
        try{
            userMapper.updateUser(user);
            System.out.println("update successfully");
            System.out.println(userMapper.selectUserById(3));
        }catch (Exception e){
            throw e;
        }
    }

}
