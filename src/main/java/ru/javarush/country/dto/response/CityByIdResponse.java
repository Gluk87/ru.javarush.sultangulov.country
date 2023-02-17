package ru.javarush.country.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.country.dto.CityDto;

@Getter
@Setter
public class CityByIdResponse extends Response {
    CityDto city;
}
