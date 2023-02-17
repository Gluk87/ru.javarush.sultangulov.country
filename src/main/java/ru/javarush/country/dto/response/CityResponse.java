package ru.javarush.country.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.country.dto.CityDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CityResponse extends Response {
    private List<CityDto> data = new ArrayList<>();
}
