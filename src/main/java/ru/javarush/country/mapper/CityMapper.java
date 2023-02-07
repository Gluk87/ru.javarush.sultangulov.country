package ru.javarush.country.mapper;

import org.springframework.stereotype.Service;
import ru.javarush.country.entity.*;
import ru.javarush.country.entity.dto.CityDto;
import ru.javarush.country.entity.response.CityByIdResponse;
import ru.javarush.country.entity.response.CityResponse;
import ru.javarush.country.entity.response.CountResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.javarush.country.configuration.Constant.*;
@Service
public class CityMapper {

    public CityResponse convertCityResponse(List<City> cityList) {
        CityResponse response = new CityResponse();
        List<CityDto> cityDtoList = getCityDtoList(cityList);
        response.setStatus(STATUS_SUCCESS);
        response.setStatusDescription(STATUS_SUCCESS_DESCR);
        response.setData(cityDtoList);
        return response;
    }

    public CountResponse convertCountResponse(int totalCount) {
        CountResponse response = new CountResponse();
        response.setStatus(STATUS_SUCCESS);
        response.setStatusDescription(STATUS_SUCCESS_DESCR);
        response.setTotalCount(totalCount);
        return response;
    }

    public CityByIdResponse convertCityByIdResponse(City city) {
        CityByIdResponse response = new CityByIdResponse();
        CityDto cityDto = getCityDto(city);
        response.setStatus(STATUS_SUCCESS);
        response.setStatusDescription(STATUS_SUCCESS_DESCR);
        response.setCity(cityDto);
        return response;
    }

    public CityResponse convertCityResponseError(String message) {
        CityResponse response = new CityResponse();
        response.setStatus(STATUS_ERROR);
        response.setStatusDescription(message);
        return response;
    }

    public CountResponse convertCountResponseError(String message) {
        CountResponse response = new CountResponse();
        response.setStatus(STATUS_ERROR);
        response.setStatusDescription(message);
        return response;
    }

    public CityByIdResponse convertCityByIdResponseError(String message) {
        CityByIdResponse response = new CityByIdResponse();
        response.setStatus(STATUS_ERROR);
        response.setStatusDescription(message);
        return response;
    }

    private List<CityDto> getCityDtoList(List<City> cityList) {
        return cityList.stream()
                .filter(Objects::nonNull)
                .map(this::getCityDto)
                .collect(Collectors.toList());
    }

    private CityDto getCityDto(City city) {
        return new CityDto(
                city.getId(),
                city.getName(),
                city.getCountry().getName(),
                city.getDistrict(),
                city.getPopulation());
    }
}
