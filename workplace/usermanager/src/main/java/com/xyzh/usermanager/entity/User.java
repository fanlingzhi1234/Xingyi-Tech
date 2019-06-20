package com.xyzh.usermanager.entity;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class User {

    public User(){

    }

    public User(String name, Integer age, java.sql.Date date,String addr) {
        this.name = name;
        this.age = age;
        this.date = date;
        this.addr = addr;
    }

    public User(Integer id, String name, Integer age, java.sql.Date date,String addr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
        this.addr = addr;
    }

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private java.sql.Date date;

    @Getter
    @Setter
    private String addr;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", Date=" + date +
                ", addr='" + addr + '\'' +
                '}';
    }
}
