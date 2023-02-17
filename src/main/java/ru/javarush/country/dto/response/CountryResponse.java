package ru.javarush.country.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.country.dto.CountryDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CountryResponse extends Response {
    private List<CountryDto> data = new ArrayList<>();
}
