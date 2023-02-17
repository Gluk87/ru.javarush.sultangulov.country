package ru.javarush.country.service;

import ru.javarush.country.dto.request.CountryRequest;
import ru.javarush.country.dto.response.CountryResponse;

public interface CountryService {

    CountryResponse getCountries(CountryRequest request);
}
