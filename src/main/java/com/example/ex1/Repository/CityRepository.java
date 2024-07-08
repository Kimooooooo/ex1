package com.example.ex1.Repository;

import com.example.ex1.Entity.City;
import com.example.ex1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

}
