package com.example.springmvcdemo;


import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/form")
public class FormController {

    @PostMapping("/form")
    public String readForm(@ModelAttribute User user) {
        return "Read x-www-form-urlencoded: " + user;
    }

    @PostMapping("/page")
    public MultiValueMap<String, String> writeForm() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("name", "lingzhi");
        map.add("addr", "chongqing");
        map.add("age", "18");
        return map;
    }
//    @GetMapping("/page")
//    public MultiValueMap<String, String> get() {
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//        map.add("name", "lingzhi");
//        map.add("addr", "chongqing");
//        map.add("age", "18");
//        return map;
//    }

    @GetMapping("/page")
    public String Page(Model model){
        ArrayList<User> userList = new ArrayList<>();
        model.addAttribute("name", "lingzhi");
        model.addAttribute("age",18);


        userList.add(new User("aaa","chognqing",13));
        userList.add(new User("bbb","shandong",13));
        userList.add(new User("ccc","shanghai",13));
        userList.add(new User("ddd","beijing",13));


        return "form";
    }



}
