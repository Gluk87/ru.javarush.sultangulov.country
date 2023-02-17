package ru.javarush.country.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.entity.*;
import ru.javarush.country.dto.request.CountryRequest;
import ru.javarush.country.dto.response.CountryResponse;
import ru.javarush.country.mapper.CountryMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryMapper countryMapper;
    private final CountryDao countryDao;

    @Override
    public CountryResponse getCountries(CountryRequest request) {
        try {
            List<Country> countryList = countryDao.getAll(request.getMaxItems());
            return countryMapper.convertCountryResponse(countryList);
        } catch (Exception e) {
            return countryMapper.convertError(e.getMessage());
        }
    }

}
