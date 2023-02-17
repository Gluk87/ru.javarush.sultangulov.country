package ru.javarush.country.service;

import ru.javarush.country.dto.request.CityByIdRequest;
import ru.javarush.country.dto.request.CityRequest;
import ru.javarush.country.dto.response.CityByIdResponse;
import ru.javarush.country.dto.response.CityResponse;
import ru.javarush.country.dto.response.CountResponse;

import java.util.List;

public interface CityService {

    CityResponse getCities(CityRequest request);

    CountResponse getCount();

    CityByIdResponse getCityById(CityByIdRequest request);

    void testMysqlData(List<Integer> ids);
}
