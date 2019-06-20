package com.xyzh.usermanager.controller;


import com.xyzh.usermanager.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/user")
public class UserController  {

    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/add")
    public String addNewUser(){
        logger.info("get in add new user page");
        return "addPage";
    }

    @GetMapping("/edit")
    public String updateUser(User user){
        logger.info("get in update user page");
        return "updatePage";
    }

    @GetMapping("/delete")
    public String deleteUser(User user){
        logger.info("delete");
        return "index";
    }

}
