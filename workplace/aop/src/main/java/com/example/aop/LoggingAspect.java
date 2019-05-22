package com.example.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//把这个类声明为一个切面：需要吧类放入到容器中,再声明为一个切面
@Order(1)
@Aspect
@Component
public class LoggingAspect {

/*
前置通知
 */
    @Before("execution(public int com.example.aop.ArithmeticCalculator.*(int, int))")
    public void beforeMethod(JoinPoint joinpoint){
        String methodName = joinpoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinpoint.getArgs());
        System.out.println("The method "+ methodName + " begins with "+args);
    }
    /*
    后置通知
     */

    @After("execution(* com.example.aop.*.*(int, int))")
    public void afterMethod(JoinPoint joinpoint){
        String methodName = joinpoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinpoint.getArgs());
        System.out.println("The method "+ methodName + " ends ");
    }

    /*
    返回通知
     */
    @AfterReturning(value = "execution(public int com.example.aop.ArithmeticCalculator.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinpoint, Object result){
        String methodName = joinpoint.getSignature().getName();
        System.out.println("The method "+ methodName + " ends with "+result);
    }
    /*
    异常抛出通知
     */

    @AfterThrowing(value = "execution(public int com.example.aop.ArithmeticCalculator.*(int, int))", throwing = "ex")
    public void afterThrowing(JoinPoint joinpoint, Exception ex){
        String methodName = joinpoint.getSignature().getName();
        System.out.println("The method "+ methodName + " ends with exceptions "+ ex);
    }
    /*
    环绕通知
     */

//    @Around("execution(public int com.example.aop.ArithmeticCalculator.*(..))")
//    public void aroundMethod(JoinPoint joinpoint ){
//
//    };
}
