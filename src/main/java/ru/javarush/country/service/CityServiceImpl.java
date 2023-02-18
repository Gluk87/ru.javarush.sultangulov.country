package ru.javarush.country.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.entity.*;
import ru.javarush.country.dto.request.CityByIdRequest;
import ru.javarush.country.dto.request.CityRequest;
import ru.javarush.country.dto.response.CityByIdResponse;
import ru.javarush.country.dto.response.CityResponse;
import ru.javarush.country.dto.response.CountResponse;
import ru.javarush.country.exception.CityNotFoundException;
import ru.javarush.country.mapper.CityMapper;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;
    private final CityDao cityDao;

    @Override
    public CityResponse getCities(CityRequest request) {
        try {
            List<City> cityList = cityDao.getAll(request.getOffset(), request.getMaxItems());
            return cityMapper.convertCityResponse(cityList);
        } catch (Exception e) {
            return cityMapper.convertCityResponseError(e.getMessage());
        }
    }

    @Override
    public CountResponse getCount() {
        try {
            int totalCount = cityDao.getTotalCount();
            return cityMapper.convertCountResponse(totalCount);
        } catch (Exception e) {
            return cityMapper.convertCountResponseError(e.getMessage());
        }
    }

    @Override
    public CityByIdResponse getCityById(CityByIdRequest request) {
        try {
            Optional<City> city = cityDao.getById(request.getId());
            if (city.isPresent()) {
                return cityMapper.convertCityByIdResponse(city.get());
            } else {
                throw new CityNotFoundException("City with id = " + request.getId() + " not found");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return cityMapper.convertCityByIdResponseError(e.getMessage());
        }
    }
}
