package ru.javarush.country.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CityResponse extends Response {
    private List<CityDto> data = new ArrayList<>();
}
