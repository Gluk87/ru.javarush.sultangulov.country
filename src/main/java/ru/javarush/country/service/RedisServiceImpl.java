package ru.javarush.country.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.dto.CityCountry;
import ru.javarush.country.entity.CountryLanguage;
import ru.javarush.country.exception.JsonProcessingRuntimeException;
import ru.javarush.country.mapper.RedisMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

@Slf4j
@AllArgsConstructor
@Service
public class RedisServiceImpl implements RedisService {

    private final SessionFactory sessionFactory;
    private final RedisMapper redisMapper;
    private final RedisClient redisClient;
    private final ObjectMapper objectMapper;
    private final CityDao cityDao;
    private static final int STEP = 500;

    @Override
    public void pushToRedis() {
        List<CityCountry> cityCountries = preparedData();
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (CityCountry cityCountry : cityCountries) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), objectMapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    log.error(e.getMessage());
                    throw new JsonProcessingRuntimeException(e.getMessage());
                }
            }

        }
    }

    @Override
    public void testRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    objectMapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    log.error(e.getMessage());
                    throw new JsonProcessingRuntimeException(e.getMessage());
                }
            }
        }
    }

    @Override
    public void shutdown() {
        if (nonNull(redisClient)) {
            redisClient.shutdown();
        }
    }

    @Override
    public void testMysqlData(List<Integer> ids) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                Optional<City> city = cityDao.getById(id);
                if (city.isPresent()) {
                    Set<CountryLanguage> languages = city.get().getCountry().getLanguages();
                }
            }
            session.getTransaction().commit();
        }
    }

    private List<CityCountry> preparedData() {
        List<City> allCities = fetchData();
        return redisMapper.transformData(allCities);
    }

    private List<City> fetchData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            int totalCount = cityDao.getTotalCount();
            int step = STEP;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDao.getAll(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}
