package com.example.ex1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Table(name = "City")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private Long id;
    @Column(name = "city_name")
    private String cityName;
    //여러개의 유저가 하나의 도시에 속함

    @OneToMany(mappedBy = "city") //User에잇는 조인컬럼한 City city 를뜻함 city객체명이바뀌면 얘도바꿔야함
    private List<User> users = new ArrayList<>();

    @Builder
    public City(Long id,String cityName){
        this.id = id;
        this.cityName = cityName;
    }



}
