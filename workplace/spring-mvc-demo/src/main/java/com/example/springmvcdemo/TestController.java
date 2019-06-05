package com.example.springmvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/test")
public class TestController {


    @GetMapping("/page")
    public String page(Model model){

        model.addAttribute("name","wangkun");

        return "index";
    }


    @PostMapping("/save")
    public String save(String username,String usernick,Model model){
        System.out.println(username);
        System.out.println(usernick);
        try {
            model.addAttribute("name",username);
            model.addAttribute("nick", usernick);
        }catch (Exception e){
            throw e;
        }


        return "index";
    }
    @PostMapping("/login")
    public String login(Model model, String mail, String pwd, MultipartFile file){


        System.out.println(mail);
        System.out.println(pwd);
        System.out.println(file.getName() +",size:"+file.getSize());
        try {
            model.addAttribute("mail",mail);
            model.addAttribute("pwd", pwd);
            //file.transferTo(new File("/data/"+file.getOriginalFilename()));

        }catch (Exception e){

        }
        return "index";
    }
}
