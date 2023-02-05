package ru.javarush.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryRequest;
import ru.javarush.country.entity.CountryResponse;
import ru.javarush.country.mapper.CountryMapper;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    CountryMapper countryMapper;

    @Autowired
    public CountryServiceImpl(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    public CountryResponse getCountries(CountryRequest request) {
        try {
            CountryDao countryDao = new CountryDao();
            List<Country> countryList = countryDao.getAll(request.getMaxItems());
            return countryMapper.convertResponse(request.getRequestId(), countryList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return countryMapper.convertError(request.getRequestId(), e);
        }
    }

}
