package ru.javarush.country.entity.response;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.country.entity.dto.CityDto;

@Getter
@Setter
public class CityByIdResponse extends Response {
    CityDto city;
}
