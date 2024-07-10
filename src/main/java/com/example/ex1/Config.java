package com.example.ex1;

import com.example.ex1.Entity.City;
import com.example.ex1.Entity.User;
import com.example.ex1.Repository.CityRepository;
import com.example.ex1.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
@Configuration
@Slf4j
public class Config implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public Config(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("데이터 주입 시작");
            if (cityRepository.count() == 0) {
                createCities();
                log.info("도시 데이터 주입 성공");
            } else {
                log.info("도시 데이터가 이미 존재합니다");
            }
            if (userRepository.count() == 0) {
                create(100);  // 100명의 사용자 생성
                log.info("사용자 데이터 주입 성공");
            } else {
                log.info("사용자 데이터가 이미 존재합니다");
            }
            log.info("데이터 주입 완료");
        } catch (Exception e) {
            log.error("데이터 주입 중 오류 발생", e);
        }
    }

    private void createCities() {
        String[] cityNames = {"서울", "부산", "인천", "대구", "대전", "광주", "울산", "세종", "시골"};
        for (String cityN : cityNames) {
            City city = City.builder()
                    .cityName(cityN)
                    .build();
            cityRepository.save(city);
            log.info("도시이름 작성완료"+city.getCityName());
        }
    }

    private void create(int count){
        List<City> cities = cityRepository.findAll();
        log.info("모든 도시값을 찾아옴"+cities);
        Random random = new Random();
        for(int i=0; i<10; i++) {
            String a = "야붕이" + (i + 1);
            String b = "비밀번호" + (i + 1);
            int c = random.nextInt(50) + 20;
            City city;
            if (i < cities.size()) {
                city = cities.get(i);
                System.out.println(city.getCityName());
            } else {
                city = City.builder()
                        .cityName("시골")
                        .build();
                System.out.println("시골");
            }

            User user = User.builder()
                    .username(a)
                    .password(b)
                    .age(c)
                    .createDate(LocalDateTime.now())
                    .city(city)
                    .build();
            userRepository.save(user);
            log.info("사용자 생성 완료: " + user.getUsername() + ", 도시: " + city.getCityName());
        }
    }
}

