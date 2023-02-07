package ru.javarush.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.*;
import ru.javarush.country.entity.request.CountryRequest;
import ru.javarush.country.entity.response.CountryResponse;
import ru.javarush.country.mapper.CountryMapper;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryMapper countryMapper;

    @Autowired
    public CountryServiceImpl(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Override
    public CountryResponse getCountries(CountryRequest request) {
        try {
            CountryDao countryDao = new CountryDao();
            List<Country> countryList = countryDao.getAll(request.getMaxItems());
            return countryMapper.convertCountryResponse(countryList);
        } catch (Exception e) {
            return countryMapper.convertError(e.getMessage());
        }
    }

}
