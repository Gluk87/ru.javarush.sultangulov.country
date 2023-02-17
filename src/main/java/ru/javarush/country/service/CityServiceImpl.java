package ru.javarush.country.service;

import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javarush.country.configuration.HibernateConfiguration;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.entity.*;
import ru.javarush.country.dto.request.CityByIdRequest;
import ru.javarush.country.dto.request.CityRequest;
import ru.javarush.country.dto.response.CityByIdResponse;
import ru.javarush.country.dto.response.CityResponse;
import ru.javarush.country.dto.response.CountResponse;
import ru.javarush.country.mapper.CityMapper;

import java.util.List;
import java.util.Set;

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
            City city = cityDao.getById(request.getId());
            return cityMapper.convertCityByIdResponse(city);
        } catch (NoResultException e) {
            return cityMapper.convertCityByIdResponseError( "City not found");
        } catch (Exception e) {
            return cityMapper.convertCityByIdResponseError(e.getMessage());
        }
    }

    public void testMysqlData(List<Integer> ids) {
        try (Session session = HibernateConfiguration.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                City city = cityDao.getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }
}
