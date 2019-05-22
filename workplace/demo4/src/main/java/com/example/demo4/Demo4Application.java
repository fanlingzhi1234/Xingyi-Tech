package com.example.demo4;

import entity.Car;
import org.springframework.context.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import entity.User;

@RestController
@SpringBootApplication
public class Demo4Application {

    public static void main(String[] args) {
        ApplicationContext act= new ClassPathXmlApplicationContext("ApplicationContext.xml");

        User user1 = (User)act.getBean("User1");
        //user1.sayHello();
        System.out.println(user1);
        Car car1=(Car)act.getBean("Car1");
        Car car2=(Car)act.getBean("Car2");
        System.out.println(car1);
        System.out.println(car2);
        //SpringApplication.run(Demo4Application.class, args);
    }


}
