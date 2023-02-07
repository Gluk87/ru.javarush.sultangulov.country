package ru.javarush.country.service;

import ru.javarush.country.entity.request.CityByIdRequest;
import ru.javarush.country.entity.request.CityRequest;
import ru.javarush.country.entity.response.CityByIdResponse;
import ru.javarush.country.entity.response.CityResponse;
import ru.javarush.country.entity.response.CountResponse;

public interface CityService {
    CityResponse getCities(CityRequest request);
    CountResponse getCount();
    CityByIdResponse getCityById(CityByIdRequest request);
}
