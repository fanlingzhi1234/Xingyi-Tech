package com.xyzh.usermanager.service;


import com.xyzh.usermanager.entity.User;

import java.sql.Date;
import java.util.List;


public interface UserService {
    User getUserById(Integer id);

    List<User> getAllUser();

    void addNewUser(User user);

    void addNewUser(String name, Integer age, String date, String addr);

    void deleteUser(Integer id);

    void updateUser(User user);
}
