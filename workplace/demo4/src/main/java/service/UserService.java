package service;

import controller.UserController;
import entity.User;

public class UserService {

    public void sayHello(User user) {
        UserController uc = new UserController();
        uc.sayHello(user);
    }

}
