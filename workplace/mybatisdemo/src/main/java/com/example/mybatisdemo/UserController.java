package com.example.mybatisdemo;

import com.example.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {



    @GetMapping("/page")
    public String Page(Model model){
        ArrayList<User> userList = new ArrayList<>();
        model.addAttribute("name", "lingzhi");
        model.addAttribute("age",18);


        userList.add(new User("aaa","chognqing",13));
        userList.add(new User("bbb","shandong",13));
        userList.add(new User("ccc","shanghai",13));
        userList.add(new User("ddd","beijing",13));


        return "";
    }

}
