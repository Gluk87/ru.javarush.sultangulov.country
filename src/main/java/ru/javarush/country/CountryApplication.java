package ru.javarush.country;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javarush.country.mapper.CityMapper;
import ru.javarush.country.mapper.RedisMapper;
import ru.javarush.country.service.CityService;
import ru.javarush.country.service.CityServiceImpl;
import ru.javarush.country.service.RedisService;

import java.util.List;

@Slf4j
@SpringBootApplication
public class CountryApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(CountryApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            RedisMapper mapper = new RedisMapper();
            RedisService redisService = new RedisService(mapper);
            redisService.pushToRedis();

            List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

            long startRedis = System.currentTimeMillis();
            redisService.testRedisData(ids);
            long stopRedis = System.currentTimeMillis();

            CityMapper cityMapper = new CityMapper();
            CityService cityService = new CityServiceImpl(cityMapper);

            long startMysql = System.currentTimeMillis();
            cityService.testMysqlData(ids);
            long stopMysql = System.currentTimeMillis();

            log.info("Redis: " + (stopRedis - startRedis) + " ms");
            log.info("MySQL: " + (stopMysql - startMysql) + " ms");

            redisService.shutdown();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
