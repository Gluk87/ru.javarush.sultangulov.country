package ru.javarush.country.service;

import org.springframework.stereotype.Service;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.CityRequest;
import ru.javarush.country.entity.CityResponse;
import ru.javarush.country.mapper.CityMapper;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityMapper cityMapper;

    public CityServiceImpl(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    public CityResponse getCities(CityRequest request) {
        try {
            CityDao cityDao = new CityDao();
            List<City> cityList = cityDao.getItems(request.getOffset(), request.getMaxItems());
            return cityMapper.convertCityResponse(cityList);
        } catch (Exception e) {
            return (CityResponse) cityMapper.convertError(e);
        }
    }
}
