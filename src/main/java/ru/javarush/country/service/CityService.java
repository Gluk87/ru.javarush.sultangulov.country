package ru.javarush.country.service;

import org.springframework.stereotype.Service;
import ru.javarush.country.entity.CityRequest;
import ru.javarush.country.entity.CityResponse;

public interface CityService {
    CityResponse getCities(CityRequest request);
}
