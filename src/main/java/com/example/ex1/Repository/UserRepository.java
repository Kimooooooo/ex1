package com.example.ex1.Repository;

import com.example.ex1.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.net.ContentHandler;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


   Page<User> findAllByOrderByIdDesc(Pageable pageable);
}
