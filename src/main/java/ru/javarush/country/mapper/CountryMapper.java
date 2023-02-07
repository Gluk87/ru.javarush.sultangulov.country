package ru.javarush.country.mapper;

import org.springframework.stereotype.Service;
import ru.javarush.country.entity.*;
import ru.javarush.country.entity.dto.CountryDto;
import ru.javarush.country.entity.response.CountryResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.javarush.country.configuration.Constant.*;

@Service
public class CountryMapper {

    public CountryResponse convertCountryResponse(List<Country> countryList) {
        CountryResponse response = new CountryResponse();
        List<CountryDto> countryDtoList = getCountryDtoList(countryList);
        response.setStatus(STATUS_SUCCESS);
        response.setStatusDescription(STATUS_SUCCESS_DESCR);
        response.setData(countryDtoList);
        return response;
    }

    public List<CountryDto> getCountryDtoList(List<Country> countryList) {
        return countryList.stream()
                .filter(Objects::nonNull)
                .map(data -> new CountryDto(
                        data.getId(),
                        data.getCode(),
                        data.getName(),
                        data.getContinent().name(),
                        data.getRegion(),
                        data.getSurfaceArea(),
                        data.getPopulation(),
                        data.getLifeExpectancy(),
                        getCityName(data.getCity()),
                        data.getLanguages()
                                .stream()
                                .filter(CountryLanguage::getIsOfficial)
                                .map(CountryLanguage::getLanguage)
                                .filter(Objects::nonNull)
                                .findFirst()
                                .orElse(""))
                )
                .collect(Collectors.toList());
    }

    public CountryResponse convertError(String message) {
        CountryResponse response = new CountryResponse();
        response.setStatus(STATUS_ERROR);
        response.setStatusDescription(message);
        return response;
    }

    private String getCityName(City city) {
        return (city != null) ? city.getName() : "";
    }
}
