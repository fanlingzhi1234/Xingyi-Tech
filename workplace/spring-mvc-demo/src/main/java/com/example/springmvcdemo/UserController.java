package com.example.springmvcdemo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;


@Controller
public class UserController {

    @GetMapping("/user/info/{name}")
    public String userinfo(@PathVariable String name){
        return "get back" + name;
    }

    @GetMapping("/user/profile")
    public User profile (){ return new User();}

    @GetMapping("/user/list")
    public List<User> getUserList(){
        return new ArrayList();
    }

    @GetMapping(path="/user/header", headers="name=lingzhifan")
    public String getByHeader() {
        return "Mapped by path + method + presence of header!";
    }


    @PostMapping("/user/profile")
    public String postName(String name){
        return "this is post method from + "+ name;
    }
    @PostMapping("/user/list")
    public User postUser(String name,String addr,Integer age){
        return new User(name,addr,age);
    }
    @PostMapping("/user/list/{name}")
    public User postUserbyName(@PathVariable String name){
        return new User(name,"123",0);
    }

    @PostMapping("/user/save")
    public User save(@RequestBody User user){
        System.out.println("name is "+user.getName());
        return user;
    }

    // 传递 list表单值
    @PostMapping("/user/list2")
    public String list(@RequestParam("listParam[]") List<String> param) {
        return "Request successful. Post param : List<String> - " + param.toString();
    }

    @PostMapping("/user/register")
    public User register(String name, String addr, Integer age){
        return new User(name,addr,age);
    }

    @PostMapping("/user/login")
    public User login(){
        return new User();
    }

    @RequestMapping(value = "/person/profile/{age}/{name}/{addr}", method = RequestMethod.GET)
    public @ResponseBody User profile(@PathVariable int age, @PathVariable String name,@PathVariable String addr){
        return new User(name,addr,age);
    }

}
