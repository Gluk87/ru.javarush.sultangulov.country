package ru.javarush.country.service;

import java.util.List;

public interface RedisService {

    void pushToRedis();

    void testRedisData(List<Integer> ids);

    void shutdown();
}
