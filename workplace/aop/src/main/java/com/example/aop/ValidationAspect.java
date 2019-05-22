package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(2)
@Aspect
@Component
public class ValidationAspect {
    @Before("execution(public int com.example.aop.ArithmeticCalculator.*(int, int))")
    public void Validation(JoinPoint joinpoint){
        List<Object> args = Arrays.asList(joinpoint.getArgs());
        System.out.println("validation  with "+args);
    }
}
