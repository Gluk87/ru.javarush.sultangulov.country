package ru.javarush.country.dao;

import ru.javarush.country.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {

    List<City> getAll(int offset, int limit);

    int getTotalCount();

    Optional<City> getById(Integer id);

}
