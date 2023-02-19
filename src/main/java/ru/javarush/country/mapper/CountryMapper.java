package ru.javarush.country.mapper;

import org.springframework.stereotype.Component;
import ru.javarush.country.entity.*;
import ru.javarush.country.dto.CountryDto;
import ru.javarush.country.dto.response.CountryResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.javarush.country.configuration.Constant.*;

@Component
public class CountryMapper {

    public CountryResponse convertCountryResponse(List<Country> countries) {
        CountryResponse response = new CountryResponse();
        List<CountryDto> countryDtoList = getCountryDtoList(countries);
        response.setStatus(STATUS_SUCCESS);
        response.setStatusDescription(STATUS_SUCCESS_DESCR);
        response.setData(countryDtoList);
        return response;
    }

    public List<CountryDto> getCountryDtoList(List<Country> countries) {
        return countries.stream()
                .filter(Objects::nonNull)
                .map(country -> new CountryDto(
                        country.getId(),
                        country.getCode(),
                        country.getName(),
                        country.getContinent().name(),
                        country.getRegion(),
                        country.getSurfaceArea(),
                        country.getPopulation(),
                        country.getLifeExpectancy(),
                        getCityName(country.getCity()),
                        country.getLanguages()
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
