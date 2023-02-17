package ru.javarush.country.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CountryDto {
    private Integer id;
    private String code;
    private String name;
    private String continent;
    private String region;
    private BigDecimal surfaceArea;
    private Integer population;
    private BigDecimal lifeExpectancy;
    private String capital;
    private String officialLanguage;
}
