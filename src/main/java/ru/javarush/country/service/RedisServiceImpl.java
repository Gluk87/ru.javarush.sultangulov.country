package ru.javarush.country.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.javarush.country.configuration.AppSessionFactory;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.dto.CityCountry;
import ru.javarush.country.mapper.RedisMapper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisMapper redisMapper;
    private final RedisClient redisClient;
    private final ObjectMapper mapper;

    public RedisServiceImpl(RedisMapper redisMapper) {
        this.redisMapper = redisMapper;
        this.redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        this.mapper = new ObjectMapper();
    }

    @Override
    public void pushToRedis() {
        List<CityCountry> cityCountries = preparedData();
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (CityCountry cityCountry : cityCountries) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
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
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
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

    private List<CityCountry> preparedData() {
        List<City> allCities = fetchData();
        return redisMapper.transformData(allCities);
    }

    private List<City> fetchData() {
        try (Session session = AppSessionFactory.getSessionFactory().getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            CityDao cityDao = new CityDao();
            session.beginTransaction();

            int totalCount = cityDao.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDao.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}
