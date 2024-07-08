package com.example.ex1.Controller;

import com.example.ex1.Entity.User;
import com.example.ex1.Entity.UserCreateDto;
import com.example.ex1.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
       List<User> users = userService.findAll(pageable);
        log.info("값을 찾아보아요 2"+users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto dto){
        User createUser = userService.create(dto);
        return new ResponseEntity<>(createUser , HttpStatus.CREATED);
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
         Optional<User> find =  userService.findById(id);
        return new ResponseEntity<>(find,HttpStatus.OK);
    }
}
