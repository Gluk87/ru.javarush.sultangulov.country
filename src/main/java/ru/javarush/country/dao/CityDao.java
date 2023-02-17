package ru.javarush.country.dao;

import ru.javarush.country.entity.City;

import java.util.List;

public interface CityDao {

    List<City> getAll(int offset, int limit);

    int getTotalCount();

    City getById(Integer id);

}
