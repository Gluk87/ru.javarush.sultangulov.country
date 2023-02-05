package ru.javarush.country.service;

import ru.javarush.country.entity.CountryRequest;
import ru.javarush.country.entity.CountryResponse;

public interface CountryService {
    CountryResponse getCountries(CountryRequest request);
}
