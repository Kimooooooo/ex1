package com.example.ex1;

import com.example.ex1.Entity.City;
import com.example.ex1.Entity.User;
import com.example.ex1.Repository.CityRepository;
import com.example.ex1.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;
@Configuration
@Slf4j
public class Config implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    @Autowired
    public Config(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("데이터 주입 시작");
            createCities();
            log.info("도시 데이터 주입성공");
            create();
            log.info("데이터 주입 성공");
        } catch (Exception e) {
            log.error("데이터 주입 중 오류 발생", e);
        }
    }
    private void createCities() {
        String[] cityNames = {"서울", "부산", "인천", "대구", "대전", "광주", "울산", "세종"};
        for (String cityN : cityNames) {
            City city = City.builder()
                    .city(cityN)
                    .build();
            City savedCity = cityRepository.save(city);
            if (savedCity != null) {
                log.info("도시이름 작성완료: " + savedCity.getCity());
            } else {
                log.error("도시 저장 실패: " + city.getCity());
            }
            log.info("도시이름 작성완료"+city.getCity());
        }
    }

    private void create(){
        List<City> cities = cityRepository.findAll();
        log.info("모든 도시값을 찾아옴"+cities);
        Random random = new Random();

        for(int i=0; i<=10; i++){
            String a = "사용자" + (i + 1);
            String b = "비밀번호" + (i + 1);
            int c = random.nextInt(100)+20;
            City city;
            if (i < 9) {
                // 랜덤한 도시 선택
                int randomIndex = random.nextInt(cities.size());
                city = cities.get(randomIndex);
            }else{
                city = City.builder()
                        .city("시골")
                        .build();
                city = cityRepository.save(city);
            }



            User user = User.builder()
                    .username(a)
                    .password(b)
                    .age(c)
                    .createDate(LocalDateTime.now())
                    .city(city)
                    .build();
           userRepository.save(user);
            log.info("사용자 생성 완료: " + user.getUsername() + ", 도시: " + city.getCity());
        }
    }
}
