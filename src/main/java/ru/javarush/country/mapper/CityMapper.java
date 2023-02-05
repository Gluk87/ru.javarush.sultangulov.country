package ru.javarush.country.mapper;

import org.springframework.stereotype.Service;
import ru.javarush.country.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CityMapper extends Mapper {

    public CityResponse convertCityResponse(List<City> cityList) {
        CityResponse response = new CityResponse();
        List<CityDto> cityDtoList = getCityDtoList(cityList);
        response.setStatus(STATUS_SUCCESS);
        response.setStatusDescription(STATUS_SUCCESS_DESCR);
        response.setData(cityDtoList);
        return response;
    }

    private List<CityDto> getCityDtoList(List<City> cityList) {
        return cityList.stream()
                .filter(Objects::nonNull)
                .map(data -> new CityDto(
                        data.getId(),
                        data.getName(),
                        data.getCountry().getName(),
                        data.getDistrict(),
                        data.getPopulation())
                )
                .collect(Collectors.toList());
    }
}
