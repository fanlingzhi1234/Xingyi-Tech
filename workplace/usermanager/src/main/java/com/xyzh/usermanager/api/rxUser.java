package com.xyzh.usermanager.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
public class rxUser {


    public rxUser(String name, Integer age, String date, String addr) {
        this.name = name;
        this.age = age;
        this.date = date;
        this.addr = addr;
    }

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    @JsonProperty
    private String date;

    @Getter
    @Setter
    private String addr;
}
