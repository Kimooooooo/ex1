package com.example.ex1.Service;

import com.example.ex1.Entity.City;
import com.example.ex1.Entity.User;
import com.example.ex1.Entity.UserCreateDto;
import com.example.ex1.Entity.UserDto;
import com.example.ex1.Repository.CityRepository;
import com.example.ex1.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    @Autowired
    public UserService(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }


    public List<UserDto> findAll(Pageable pageable){
        List<User> users = userRepository.findAllByOrderByIdDesc(pageable).getContent();
        List<UserDto> usersDto = users.stream().map(UserDto::new).collect(Collectors.toList());
        log.info("Found {} users", usersDto.size());
        return usersDto;
    }
    public UserDto findById(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을수없습니다"));
        return new UserDto(findUser);
    }
    public UserDto create(UserCreateDto dto){
        City city = cityRepository.findById(dto.getCityId()).orElseThrow(() -> new EntityNotFoundException("도시를 찾을수없습니다"));
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .age(dto.getAge())
                .createDate(LocalDateTime.now())
                .city(city)
                .build();
        User saveUser = userRepository.save(user);
        return new UserDto(saveUser);

    }
    public UserDto update(Long id,UserDto dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("수정할 아이디가 없습니다"));
        user.updateMethod(dto.getUsername(),(dto.getPassword()), dto.getAge());
       User updateUser =  userRepository.save(user);
        return new UserDto(updateUser);
    }
    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("삭제할 아이디가 없습니다"));
        userRepository.delete(user);
    }
}
