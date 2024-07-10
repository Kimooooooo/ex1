package com.example.ex1.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateDto {
    @NotBlank(message = "이름은 필수입니다")
    private String username;
    @NotBlank(message = "비밀번호는 필수입니다")
    @Size(min = 8,message = "비밀번호는 최소8자입니다")
    private String password;
    @Min(value = 10,message = "10살 미만은 가입불가입니다")
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
