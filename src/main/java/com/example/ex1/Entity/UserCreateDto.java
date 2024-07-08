package com.example.ex1.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateDto {
    private String username;
    private String password;
    private int age;
    private Long cityId;
    @Builder
    public UserCreateDto(String username,String password,int age,Long cityId){
        this.username = username;
        this.password = password;
        this.age = age;
        this.cityId = cityId;

    }



}
