package com.example.ex1.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name = "User")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_pass")
    private String password;
    @Column(name = "user_age")
    private int age;
    @CreatedDate
    @Column(name = "user_createDate")
    private LocalDateTime createDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Builder
    public User(Long id, String username, String password, int age, LocalDateTime createDate, City city) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.createDate = createDate;
        this.city = city;
    }

    public void updateMethod(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }





}
