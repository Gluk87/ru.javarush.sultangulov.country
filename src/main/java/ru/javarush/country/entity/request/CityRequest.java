package ru.javarush.country.entity.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {
    private Integer maxItems;
    private Integer offset;
}
