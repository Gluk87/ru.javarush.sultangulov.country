package ru.javarush.country.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryRequest {
    private Integer requestId;
    private Integer maxItems;
}
