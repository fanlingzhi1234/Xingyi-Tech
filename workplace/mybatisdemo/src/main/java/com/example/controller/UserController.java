package com.example.controller;

import com.example.beans.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getById")
    public String getById(Integer id,Model model){


        User user = userService.getUserById(1);

        System.out.println(user);
        model.addAttribute("name",user.getName());
        model.addAttribute("age",user.getAge());
        model.addAttribute("id",user.getId());
        model.addAttribute("addr",user.getAddr());
        model.addAttribute("date",user.getDate());

        return "test";
    }

    @GetMapping("/getAllUser")
    public String getAllUser(ModelMap modelMap){
        List<User> userlist = userService.getAllUser();
        System.out.println(userlist);
        //modelMap.put("userlist",userlist);
        modelMap.addAttribute("userlist", userlist);


        return "test";
    }

    @PostMapping("/addNewUser")
    public void addNewUser(String newName, Integer newAge,String newAddr){

        Date date = new Date();
        System.out.println("name"+newName+",age"+newAge+",addr"+newAddr);
        try{
            userService.addNewUser(newName,newAge,date,newAddr);

        }catch (Exception e){
            System.out.println("insert failure");
            throw e;
        }
        System.out.println("insert successfully");


    }

    @PostMapping("/deleteUser")
    public void deleteUser(Integer id){
        try{
            userService.deleteUser(id);
        }catch(Exception e){
            throw e;
        }
    }

    @PostMapping("/updateUser")
    public void updateUser(Integer id,String name, Integer age,Date date,String addr){

        //id 和 date 回不来
        User user = new User(id,name,age,date,addr);
        System.out.println(user);
        try{
            userService.updateUser(user);
        }catch(Exception e){
            System.out.println("update failure");
            throw e;
        }
        System.out.println("update successfully");
    }


}
