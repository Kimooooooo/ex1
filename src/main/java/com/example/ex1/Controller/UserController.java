package com.example.ex1.Controller;

import com.example.ex1.Entity.User;
import com.example.ex1.Entity.UserCreateDto;
import com.example.ex1.Entity.UserDto;
import com.example.ex1.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/api/v1/")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("users") // 모든유저조회
    public ResponseEntity<List<UserDto>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
       List<UserDto> users = userService.findAll(pageable);
        log.info("값을 찾아보아요 2"+users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("users") //생성
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto dto){
        UserDto createUser = userService.create(dto);
        return new ResponseEntity<>(createUser , HttpStatus.CREATED);
    }
    @GetMapping("users/{id}") //특정id를 가진 유저 조회
    public ResponseEntity<UserDto>getUserById(@PathVariable Long id) {
          UserDto find =  userService.findById(id);
        return new ResponseEntity<>(find,HttpStatus.OK);
    }
    @PatchMapping("users/{id}") //특정id를 가진 유저 수정
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long id, @RequestBody UserDto dto){
        return new ResponseEntity<>(userService.update(id,dto),HttpStatus.OK);

    }
    @DeleteMapping("users/{id}") //특정 id를 가진 유저 삭제
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
