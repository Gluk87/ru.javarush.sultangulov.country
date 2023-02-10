package ru.javarush.country.service;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.javarush.country.configuration.AppSessionFactory;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.entity.*;
import ru.javarush.country.entity.request.CityByIdRequest;
import ru.javarush.country.entity.request.CityRequest;
import ru.javarush.country.entity.response.CityByIdResponse;
import ru.javarush.country.entity.response.CityResponse;
import ru.javarush.country.entity.response.CountResponse;
import ru.javarush.country.mapper.CityMapper;

import java.util.List;
import java.util.Set;

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
            return cityMapper.convertCityResponseError(e.getMessage());
        }
    }

    @Override
    public CountResponse getCount() {
        try {
            CityDao cityDao = new CityDao();
            int totalCount = cityDao.getTotalCount();
            return cityMapper.convertCountResponse(totalCount);
        } catch (Exception e) {
            return cityMapper.convertCountResponseError(e.getMessage());
        }
    }

    @Override
    public CityByIdResponse getCityById(CityByIdRequest request) {
        try {
            CityDao cityDao = new CityDao();
            City city = cityDao.getById(request.getId());
            return cityMapper.convertCityByIdResponse(city);
        } catch (NoResultException e) {
            return cityMapper.convertCityByIdResponseError( "City not found");
        } catch (Exception e) {
            return cityMapper.convertCityByIdResponseError(e.getMessage());
        }
    }

    public void testMysqlData(List<Integer> ids) {
        try (Session session = AppSessionFactory.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            CityDao cityDao = new CityDao();
            for (Integer id : ids) {
                City city = cityDao.getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }
}
