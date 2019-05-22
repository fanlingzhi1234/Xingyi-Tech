package com.example.aop;

import org.springframework.context.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.tools.java.ClassPath;
import com.example.aop.ArithmeticCalculator;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {

        //1. 创建Spring 的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        //2. 从IOC容器中获取Bean的实例
        ArithmeticCalculator arithmeticCalculator = ctx.getBean(ArithmeticCalculator.class);
        //3. 使用bean
        int result = arithmeticCalculator.add(3,6);
        System.out.println("result : "+result);
        result = arithmeticCalculator.divide(9,110);
        System.out.println("result : "+result);
        //SpringApplication.run(AopApplication.class, args);
    }

}
