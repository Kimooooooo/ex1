package com.example.ex1.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private int age;
    private LocalDateTime createDate;
    private String city;

    public UserDto (User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.age = user.getAge();
        this.createDate = user.getCreateDate();
        this.city = user.getCity().getCityName();
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CityDto{

        private Long id;
        private String cityName;

        public CityDto(City city){
            this.id = city.getId();
            this.cityName = city.getCityName();
        }

    }

}
