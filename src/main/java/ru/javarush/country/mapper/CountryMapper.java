package ru.javarush.country.mapper;

import org.springframework.stereotype.Service;
import ru.javarush.country.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CountryMapper extends Mapper {

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

    public String getCityName(City city) {
        return (city != null) ? city.getName() : "";
    }
}
