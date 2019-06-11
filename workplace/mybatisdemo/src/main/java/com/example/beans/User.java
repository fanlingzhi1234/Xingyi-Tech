package com.example.beans;

import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String addr;
    private Date date;
    public User(){

    }

    public User(Integer id, String name, Integer age, Date date,String addr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addr = addr;
        this.date = date;
    }
    public User(String name, Integer age, Date date,String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", date=" + date +
                '}';
    }
}
