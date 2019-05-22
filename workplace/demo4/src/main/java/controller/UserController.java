package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import entity.User;

@Controller
@RequestMapping("/web")

public class UserController {

    @RequestMapping("/")
    public String sayHello(User user){
        System.out.println("say hello from");
        return user.getName();
    }
}
