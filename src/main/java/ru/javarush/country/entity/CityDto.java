package ru.javarush.country.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CityDto {
    private Integer id;
    private String name;
    private String country;
    private String district;
    private Integer population;
}
