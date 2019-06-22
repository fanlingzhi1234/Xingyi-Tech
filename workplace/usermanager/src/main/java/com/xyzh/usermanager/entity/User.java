package com.xyzh.usermanager.entity;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class User {

    public User(){

    }

    public User(String name, Integer age, String createDate, String addr) {
        this.name = name;
        this.age = age;
        this.createDate = createDate;
        this.addr = addr;
    }

    public User(Integer id, String name, Integer age, String createDate, String addr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createDate = createDate;
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
    private String createDate;

    @Getter
    @Setter
    private String addr;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", createDate=" + createDate +
                ", addr='" + addr + '\'' +
                '}';
    }
}
