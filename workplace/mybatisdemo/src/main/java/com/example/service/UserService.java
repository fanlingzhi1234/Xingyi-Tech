package com.example.service;

import com.example.beans.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    User getUserById(Integer id);

    List<User> getAllUser();

    void addNewUser(String name, Integer age, Date date, String addr);

    void deleteUser(Integer id);

    void updateUser(User user);
}
