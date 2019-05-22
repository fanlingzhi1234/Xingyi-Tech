package com.example.aop;

import org.springframework.stereotype.Component;

@Component
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j){
        int result = i + j;
        return result;
    }
    @Override
    public int minus(int i, int j){
        int result = i - j;
        return result;
    }
    @Override
    public int multi(int i, int j){
        int result = i * j;
        return result;
    }
    @Override
    public int divide(int i, int j){
        int result = i / j;
        return result;
    }

}
