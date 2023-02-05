package ru.javarush.country.mapper;

import org.springframework.stereotype.Service;
import ru.javarush.country.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CountryMapper {

    public CountryResponse convertResponse(Integer requestId, List<Country> countryList) {
        CountryResponse response = new CountryResponse();
        List<CountryDto> countryDtoList = getCountryDtoList(countryList);
        response.setRequestId(requestId);
        response.setStatus("SUCCESS");
        response.setStatusDescription("Операция прошла успешно");
        response.setCountryDtoList(countryDtoList);
        return response;
    }

    public CountryResponse convertError(Integer requestId, Exception exception) {
        CountryResponse response = new CountryResponse();
        response.setRequestId(requestId);
        response.setStatus("ERROR");
        response.setStatusDescription(exception.getMessage());
        return response;
    }

    public List<CountryDto> getCountryDtoList(List<Country> countryList) {
        return countryList.stream()
                .filter(Objects::nonNull)
                .map(data -> new CountryDto(
                        data.getId(),
                        data.getCode(),
                        data.getName(),
                        String.valueOf(data.getContinent()),
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
