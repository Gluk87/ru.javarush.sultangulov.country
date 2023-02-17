package ru.javarush.country.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CityDto {
    private Integer id;
    private String name;
    private String country;
    private String district;
    private Integer population;
}
