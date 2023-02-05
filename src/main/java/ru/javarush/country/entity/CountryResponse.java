package ru.javarush.country.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CountryResponse extends Response {
    private List<CountryDto> countryDtoList = new ArrayList<>();
}
