package com.example.ex1.Service;

import com.example.ex1.Entity.City;
import com.example.ex1.Entity.User;
import com.example.ex1.Entity.UserCreateDto;
import com.example.ex1.Repository.CityRepository;
import com.example.ex1.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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


    public List<User> findAll(Pageable pageable){
        List<User> users = userRepository.findAllByOrderByIdDesc(pageable).getContent();
        log.info("Found {} users", users.size());
        return users;
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);

    }
    public User create(UserCreateDto dto){
        City city = cityRepository.findById(dto.getCityId()).orElseThrow(() -> new EntityNotFoundException("도시를 찾을수없습니다"));
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .age(dto.getAge())
                .createDate(LocalDateTime.now())
                .city(city)
                .build();
        return userRepository.save(user);

    }
    public User update(Long id,User user){
         Optional<User> find = userRepository.findById(id);
         if(find.isPresent()){
            User finder = find.get();
            finder.updateMethod(user.getUsername(),user.getPassword(), user.getAge());
            userRepository.save(finder);
        }else{
             throw new IllegalStateException("수정할 아이디가없습니다");
         }
          return user;
    }
    public void delete(Long id){
        Optional<User> finder = userRepository.findById(id);
        if(finder.isPresent()){
             userRepository.delete(finder.get());
        }else{
            throw new IllegalStateException("삭제할 아이디가없습니다");
        }


    }
}
