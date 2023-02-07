package ru.javarush.country.service;

import ru.javarush.country.entity.request.CountryRequest;
import ru.javarush.country.entity.response.CountryResponse;

public interface CountryService {
    CountryResponse getCountries(CountryRequest request);
}
